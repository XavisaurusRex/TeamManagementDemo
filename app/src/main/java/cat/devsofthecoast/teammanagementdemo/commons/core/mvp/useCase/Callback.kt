package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase

interface Callback<R> {
    fun onSuccess(result: R)

    fun onError(t: Throwable)
}