package id.shobrun.stikieventorganizer.ui.myevents.scanner


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.utils.DialogTools
import id.shobrun.stikieventorganizer.utils.DialogTools.CallbackDialog
import id.shobrun.stikieventorganizer.utils.DialogTools.CallbackShare
import id.shobrun.stikieventorganizer.utils.Tools
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber
import com.google.zxing.Result;


class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private val TAG = javaClass.simpleName
    private var mScannerView: ZXingScannerView? = null
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view
        setContentView(mScannerView) // Set the scanner view as the content view
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
        Timber.d(TAG, rawResult.getText()) // Prints scan results
        Timber.d(
            TAG,
            rawResult.getBarcodeFormat().toString()
        ) // Prints the scan format (qrcode, pdf417 etc.)
        //        Toast.makeText(this, rawResult.getText()+"-"+rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        val data: String = rawResult.getText()
        val tools = Tools(applicationContext)
        val result =
            tools.matcherQRData(data)
        val dialogTools = DialogTools(this)
        var callbackDialog: CallbackDialog? = object: CallbackDialog{
            override fun onPositiveClick(dialog: Dialog?) {
                finish()
            }

            override fun onNegativeClick(dialog: Dialog?) {
                finish()
            }

            override fun onSearchClick(dialog: Dialog?) {
                finish()
            }

        }
        var callbackShare: CallbackShare? = null
        var content = ""

        val dialog: Dialog = dialogTools.buildDialogInfo(
            "Hasil Scan",
            content,
            result.first,
            result.second,
            R.drawable.puppy,
            callbackDialog,
            callbackShare
        )
        dialog.show()
        // If you would like to resume scanning, call this method below:
        mScannerView!!.resumeCameraPreview(this)
    }
}