package id.ac.stiki.doleno.stikieventorganizer.repository.local

interface ILocalSource {
    fun <T> insertsLocal(obj: List<T>)
    fun <T> insertLocal(obj: T)
    fun <T> updateLocal(obj: T): Int
    fun <T> deleteLocal(obj: T)
}