package cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.SetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.SignupContract

class SignupPresenter(
        private val appConfig: BaseConfig,
        private val setTrainerUseCase: SetTrainerUseCase)
    : SignupContract.Presenter() {

    override fun createUpdateTrainer(trainer: Trainer) {
        SetTrainerUseCase.Executor(appConfig) {
            useCase = setTrainerUseCase
            onSuccess = {
                view?.onUpdateCreatePlayerSuccess()
            }
            onError = {
                view?.onUpdateCreatePlayerError(it)
            }
        }.execute(trainer)
    }
}