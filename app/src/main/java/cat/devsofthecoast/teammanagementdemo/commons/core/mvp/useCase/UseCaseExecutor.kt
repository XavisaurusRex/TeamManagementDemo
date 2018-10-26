package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig

@Suppress("MemberVisibilityCanBePrivate")
open class UseCaseExecutor<I, R>(
        config: BaseConfig,
        builder: (UseCaseExecutor<I, R>.() -> Unit)?
) {
    var useCase: UseCase<I, R>? = null
    var onSuccess: ((R) -> Unit)? = null
    var onError: ((Throwable) -> Unit)? = null
    var threadExecutor: ThreadExecutor = config.getThreadExecutor()
    var postExecutor: PostExecutor = config.getPostExecutor()
    var errorHandler: ErrorHandler? = config.getErrorHandler()

    init {
        builder?.invoke(this)
    }

    fun execute(input: I?) {
        check(useCase != null) { "A valid UseCase reference should be provided." }

        threadExecutor.execute {
            useCase?.run(input, object : Callback<R> {
                override fun onSuccess(result: R) {
                    try {
                        postExecutor.execute {
                            onSuccess?.invoke(result)
                        }
                    } catch (t: Throwable) {
                        handleError(t)
                    }
                }

                override fun onError(t: Throwable) {
                    handleError(t)
                }
            })
        }
    }

    fun cancel() {
        onSuccess = null
        onError = null
    }

    private fun handleError(t: Throwable) {
        postExecutor.execute {
            when {
                onError != null -> onError?.invoke(t)
                errorHandler != null -> errorHandler?.onError(t)
                else -> throw t
            }
        }
    }
}