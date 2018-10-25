package cat.devsofthecoast.teammanagementdemo.core.mvp.useCase

interface Callback<R> {
    fun onSuccess(result: R)

    fun onError(t: Throwable)
}