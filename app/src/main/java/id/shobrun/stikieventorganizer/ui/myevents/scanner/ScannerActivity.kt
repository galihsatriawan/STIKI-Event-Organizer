package id.shobrun.stikieventorganizer.ui.myevents.scanner


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.zxing.Result
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.DialogConfirmTicketBinding
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.utils.DialogTools
import id.shobrun.stikieventorganizer.utils.DialogTools.CallbackDialog
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber
import javax.inject.Inject


class ScannerActivity : DaggerAppCompatActivity(), ZXingScannerView.ResultHandler {
    private val TAG = javaClass.simpleName
    private var mScannerView: ZXingScannerView? = null
    val REQUEST_CAMERA = 101
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    val viewModel:ScannerViewModel by viewModels{viewModelFactory}
    lateinit var binding: DialogConfirmTicketBinding
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view

        setContentView(mScannerView) // Set the scanner view as the content view
        checkCameraPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) { // permission was granted, yay! Do the
// contacts-related task you need to do.
                } else { // permission denied, boo! Disable the
// functionality that depends on this permission.
                }
                return
            }
        }
    }


    fun checkCameraPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) { // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) { // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                    .setTitle(R.string.title_camera_permission)
                    .setMessage(R.string.text_camera_permission)
                    .setPositiveButton(R.string.OK
                    ) { dialogInterface, i ->
                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(
                            this@ScannerActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA
                        )
                    }
                    .create()
                    .show()
            } else { // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA
                )
            }
            false
        } else {
            true
        }
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera() // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera() // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) { // Do something with the result here
        Timber.d(TAG, rawResult.text) // Prints scan results
        Timber.d(
            TAG,
            rawResult.barcodeFormat.toString()
        ) // Prints the scan format (qrcode, pdf417 etc.)
               // Toast.makeText(this, rawResult.text +"-"+rawResult.barcodeFormat.toString(), Toast.LENGTH_SHORT).show()
        Timber.d(rawResult.text +"-"+rawResult.barcodeFormat.toString())
        rawResult.text
        val data: String = rawResult.text
        Timber.d("$data")
        var invitation : Invitation? = null
        try {
            invitation= Gson().fromJson(data,Invitation::class.java)
        }catch (e : Exception){
            Toast.makeText(this,"Please place your camera properly",Toast.LENGTH_SHORT).show()
        }

        val resDialog = Dialog(this as Activity)
        resDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this as Activity),R.layout.dialog_confirm_ticket,null,false)
        resDialog.setCancelable(true)
        resDialog.setContentView(binding.root)
        val closeButton : MaterialButton = resDialog.findViewById(R.id.btn_negative)
        closeButton.setOnClickListener{
            resDialog.dismiss()
        }
        with(binding){
            vm = viewModel
            lifecycleOwner = this@ScannerActivity
        }
        resDialog.show()

        viewModel.postInvitation(invitation)
        // If you would like to resume scanning, call this method below:
        mScannerView!!.resumeCameraPreview(this)
    }
}