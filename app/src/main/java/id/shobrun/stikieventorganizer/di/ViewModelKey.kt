package id.shobrun.stikieventorganizer.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@Target(AnnotationTarget.FUNCTION)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)