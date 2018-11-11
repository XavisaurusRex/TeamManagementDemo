package cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.impl

import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.DailyEntriesRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.dailyentries.impl.DailyEntriesServiceImpl

class DailyEntriesRepositoryImpl : DailyEntriesRepository {
    private val service = DailyEntriesServiceImpl()

    override fun setDailyEntries(dailyEntries: List<DailyEntry>, listener: ServiceCallback<Void?>?) {
        service.setDailyEntries(dailyEntries, listener)
    }
}