package cat.devsofthecoast.teammanagementdemo.commons.services.dailyentries

import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface DailyEntriesService {
    fun setDailyEntries(dailyEntries: List<DailyEntry>, listener: ServiceCallback<Void?>?)
}