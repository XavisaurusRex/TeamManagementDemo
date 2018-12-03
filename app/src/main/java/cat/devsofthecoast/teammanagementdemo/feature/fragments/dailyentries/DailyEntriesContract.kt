package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer

interface DailyEntriesContract {
    interface View : BaseView {
        fun onGetDailyEntriesSuccess(dailyEntries: List<DailyEntry>)
        fun onGetDailyEntriesError(throwable: Throwable)
        fun onGetLoggedTrainerSuccess(trainer: Trainer)
        fun onGetLoggedTrainerError(throwable: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getDailyEntries(teamKey: String?)
        abstract fun getLoggedTrainer()
    }
}