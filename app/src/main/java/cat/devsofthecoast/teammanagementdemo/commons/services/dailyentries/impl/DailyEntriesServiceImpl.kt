package cat.devsofthecoast.teammanagementdemo.commons.services.dailyentries.impl

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.dailyentries.DailyEntriesService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference

class DailyEntriesServiceImpl: BaseService(), DailyEntriesService {
    private val DAILY_ENTRIES_LOCATION = "dailyentries"
    override val refTable: DatabaseReference = firebaseDatabase.child(DAILY_ENTRIES_LOCATION)

    override fun setDailyEntries(dailyEntries: List<DailyEntry>, listener: ServiceCallback<Void?>?) {
        val keysEntries = mutableMapOf<String, DailyEntry>()
        for (dailyEntry: DailyEntry in dailyEntries) {
            keysEntries[dailyEntry.key!!] = dailyEntry
        }
        if (keysEntries.isNotEmpty()) {
            addNewDataList(keysEntries, OnCompleteListener {
                when {
                    it.isSuccessful -> {
                        listener?.onSuccess(null)
                    }
                    it.isCanceled -> listener?.onError(PostingFirebaseException())
                    else -> listener?.onError(PostingFirebaseException())
                }
            })
        } else {
            listener?.onError(PostingFirebaseException())
        }
    }

    override fun getDailyEntries(teamKey: String, listener: ServiceCallback<ArrayList<DailyEntry>>) {
        getSingleSnapShot(refTable){ it ->
            val listDailies: ArrayList<DailyEntry> = arrayListOf()
            it?.children?.forEach {dailySnapshot ->
                listDailies.add(dailySnapshot.getValue(DailyEntry::class.java)!!)
            }
            listener.onSuccess(listDailies)
        }
    }
}