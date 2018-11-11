package cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries

import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface DailyEntriesRepository {
    fun setDailyEntries(dailyEntries: List<DailyEntry>, listener: ServiceCallback<Void?>?)
}