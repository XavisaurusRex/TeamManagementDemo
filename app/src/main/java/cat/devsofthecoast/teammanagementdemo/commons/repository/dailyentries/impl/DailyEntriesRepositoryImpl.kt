package cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.impl

import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.DailyEntriesRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.dailyentries.impl.DailyEntriesServiceImpl

class DailyEntriesRepositoryImpl : DailyEntriesRepository {

    private val dailyEntriesCache = TMDCache<String, ArrayList<DailyEntry>>()

    private val service = DailyEntriesServiceImpl()

    override fun setDailyEntries(dailyEntries: List<DailyEntry>, listener: ServiceCallback<Void?>?) {
        service.setDailyEntries(dailyEntries, listener)
    }

    override fun getDailyEntries(teamKey: String, listener: ServiceCallback<ArrayList<DailyEntry>>) {
        if(dailyEntriesCache.contains(teamKey)){
            listener.onSuccess(dailyEntriesCache[teamKey]!!)
        } else {
            service.getDailyEntries(teamKey, listener)
        }
    }
}