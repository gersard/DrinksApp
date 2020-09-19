package cl.gerardomascayano.drinksapp.core

import androidx.annotation.StringRes

sealed class Resource<out T> {
    data class Loading<out T>(val isLoading: Boolean) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    class Empty<out T> : Resource<T>()
    data class Failure<out T>(@StringRes val messageRes: Int) : Resource<T>()
}