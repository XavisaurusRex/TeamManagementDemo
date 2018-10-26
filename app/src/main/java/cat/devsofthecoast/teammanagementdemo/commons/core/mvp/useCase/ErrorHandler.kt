package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase

interface ErrorHandler {
    fun onError(t: Throwable)
}