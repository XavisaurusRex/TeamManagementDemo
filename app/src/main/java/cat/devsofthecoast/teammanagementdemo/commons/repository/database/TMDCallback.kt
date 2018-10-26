package cat.devsofthecoast.teammanagementdemo.commons.repository.database

typealias TMDCallback<I,O> = ((success: I, error: O?) -> Unit)

//TODO must implement better callback
//interface TMDCallback<T> {
//    fun onSuccess(value: T)
//    fun <E : Throwable> onError(error: E)
//}