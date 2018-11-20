package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import com.google.firebase.auth.FirebaseAuth

class HeadPresenter(
        private val appConfig: BaseConfig,
        private val getTeamUseCase: GetTeamUseCase,
        private val getTrainerUseCase: GetTrainerUseCase)
    : HeadContract.Presenter() {

    override fun getTeam(teamKey: String) {
        GetTeamUseCase.Executor(appConfig) {
            useCase = getTeamUseCase
            onSuccess = {
                view?.onGetTeamSuccess(it)
            }
            onError = {
                view?.onGetTeamError(it)
            }
        }.execute(teamKey)
    }

    override fun getLoggedTrainer(){
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
}