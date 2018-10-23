package cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.usecase.FillDatabaseUseCase

class DevOptionsPresenter(
        private val appConfig: BaseConfig,
        private val fillDatabaseUseCase: FillDatabaseUseCase)
    : DevOptionsContract.Presenter() {
    override fun fillDatabase() {
        FillDatabaseUseCase.Executor(appConfig) {
            useCase = fillDatabaseUseCase
            onSuccess = {
                view?.onDatabaseFilledSuccess()
            }
            onError = {
                view?.onDatabaseFilledError(it)
            }
        }.execute(null)
    }
}