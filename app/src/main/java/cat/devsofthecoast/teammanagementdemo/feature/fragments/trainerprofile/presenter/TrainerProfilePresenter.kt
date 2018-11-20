package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.UpdateTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.TrainerProfileContract
import com.google.firebase.auth.FirebaseAuth

class TrainerProfilePresenter(
        private val appConfig: BaseConfig,
        private val getTrainerUseCase: GetTrainerUseCase,
        private val updateTrainerUseCase: UpdateTrainerUseCase,
        private val getTeamsUseCase: GetTeamsUseCase)
    : TrainerProfileContract.Presenter() {

    override fun getLoggedTrainer() {
        val trainerKey = FirebaseAuth.getInstance().uid
        GetTrainerUseCase.Executor(appConfig) {
            useCase = getTrainerUseCase
            onSuccess = {
                view?.onGetLoggedTrainerSuccess(it)
            }
            onError = {
                view?.onGetLoggedTrainerError(it)
            }
        }.execute(trainerKey)
    }

    override fun getAllTeams(){
        GetTeamsUseCase.Executor(appConfig) {
            useCase = getTeamsUseCase
            onSuccess = {
                view?.onGetTeamsSuccess(it)
            }
            onError = {
                view?.onGetTeamsError(it)
            }
        }.execute(null)
    }

    override fun updateTrainer(trainer: Trainer) {
        UpdateTrainerUseCase.Executor(appConfig) {
            useCase = updateTrainerUseCase
            onSuccess = {
                view?.onUpdateTrainerSuccess()
            }
            onError = {
                view?.onUpdateTrainerError(it)
            }
        }.execute(trainer)
    }
}