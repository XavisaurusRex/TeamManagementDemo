package cat.devsofthecoast.teammanagementdemo.core.mvp.useCase

interface ErrorHandler {
    fun onError(t: Throwable)
}