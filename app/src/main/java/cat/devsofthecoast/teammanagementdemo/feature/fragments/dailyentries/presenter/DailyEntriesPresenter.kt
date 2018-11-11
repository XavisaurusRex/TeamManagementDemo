package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.DailyEntriesContract

class DailyEntriesPresenter(
        private val appConfig: BaseConfig)
    : DailyEntriesContract.Presenter() {
    override fun getDailyEntries(team: Team) {
        //TODO HERE SHOULD CALL WS
        val list: ArrayList<DailyEntry> = arrayListOf()
        for(i in 0 until 10){
            val dailyEntry = DailyEntry()
            dailyEntry.timestamp = 100 * i
            list.add(dailyEntry)
        }

        view?.onGetDailyEntriesSuccess(list)
    }
}