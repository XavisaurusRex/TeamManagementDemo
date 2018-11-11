package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team

interface DailyEntriesContract {
    interface View : BaseView {
        fun onGetDailyEntriesSuccess(dailyEntries: List<DailyEntry>)
        fun onGetDailyEntriesError(throwable: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getDailyEntries(team: Team)
    }
}