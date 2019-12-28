package id.shobrun.stikieventorganizer.extensions

import android.view.View
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status

import org.jetbrains.anko.design.snackbar
import timber.log.Timber

inline fun <reified T> View.bindResource(resource: Resource<T>?, onSuccess: (Resource<T>) -> Unit) {
    if (resource != null) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> onSuccess(resource)
            Status.ERROR -> {
                Timber.d(resource.errorEnvelope?.status_message.toString())
                snackbar(this.context.resources.getString(R.string.failed_load))
            }
        }
    }
}
