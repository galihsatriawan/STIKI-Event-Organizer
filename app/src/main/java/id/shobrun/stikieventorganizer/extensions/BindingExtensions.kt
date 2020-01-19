package id.shobrun.stikieventorganizer.extensions

import android.view.View
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status

import org.jetbrains.anko.design.snackbar
import timber.log.Timber

inline fun <reified T , reified S> View.bindResource(resource: Resource<T,S>?, onSuccessOrError: (Resource<T,S>) -> Unit) {
    if (resource != null) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> onSuccessOrError(resource)
            Status.ERROR -> {
                onSuccessOrError(resource)
                snackbar(this.context.resources.getString(R.string.failed_load))
            }
        }
    }
}
