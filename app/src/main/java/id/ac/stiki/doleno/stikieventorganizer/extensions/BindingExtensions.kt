package id.ac.stiki.doleno.stikieventorganizer.extensions

import android.view.View
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import org.jetbrains.anko.design.snackbar

inline fun <reified T, reified S> View.bindResource(
    resource: Resource<T, S>?,
    onSuccessOrError: (Resource<T, S>) -> Unit
) {
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
