package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.useCase.dailyEntries.GetDailyEntriesUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.DailyEntriesContract
import com.google.firebase.auth.FirebaseAuth

class DailyEntriesPresenter(
        private val appConfig: BaseConfig,
        private val getTrainerUseCase: GetTrainerUseCase,
        private val getDailyEntriesUseCase: GetDailyEntriesUseCase)
    : DailyEntriesContract.Presenter() {
    override fun getDailyEntries(teamKey: String?) {
        GetDailyEntriesUseCase.Executor(appConfig) {
            useCase = getDailyEntriesUseCase
            onSuccess = {
                view?.onGetDailyEntriesSuccess(it)
            }
            onError = {
                view?.onGetDailyEntriesError(it)
            }
        }.execute(teamKey)
    }

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
}