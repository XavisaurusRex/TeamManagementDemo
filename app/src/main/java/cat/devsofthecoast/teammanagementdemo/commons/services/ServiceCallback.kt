package cat.devsofthecoast.teammanagementdemo.commons.services

interface ServiceCallback<T> {
    fun onSuccess(value: T)
    fun <E : Throwable> onError(error: E)
}