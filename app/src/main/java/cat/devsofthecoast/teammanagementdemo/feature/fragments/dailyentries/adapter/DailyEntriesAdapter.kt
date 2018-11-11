package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.controllers.recycler.AdapterList
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import kotlinx.android.synthetic.main.item_dailyentry_view.view.*

class DailyEntriesAdapter(
        private val context: Context
) : RecyclerView.Adapter<DailyEntriesAdapter.ViewHolder>(), AdapterList<DailyEntry> {

    val dailyEntries: ArrayList<DailyEntry> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DailyEntriesAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dailyentry_view, parent, false))
    }

    override fun getItemCount(): Int {
        return dailyEntries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvTimeStamp.text = dailyEntries[position].key
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun add(dailyEntry: DailyEntry) {
        this.dailyEntries.add(dailyEntry)
        this.notifyItemChanged(dailyEntries.size - 1)
    }

    override fun add(dailyEntries: List<DailyEntry>) {
        this.dailyEntries.addAll(dailyEntries)
        this.notifyItemRangeInserted(this.dailyEntries.size - dailyEntries.size, this.dailyEntries.size)
    }

    override fun remove(dailyEntry: DailyEntry) {
        this.dailyEntries.add(dailyEntry)
        this.notifyItemChanged(dailyEntries.size - 1)
    }

    override fun removeAt(position: Int) {
        this.dailyEntries.removeAt(position)
        this.notifyItemChanged(position)
    }

    override fun size(): Int {
        return dailyEntries.size
    }

    override fun removeAll() {
        dailyEntries.clear()
        notifyDataSetChanged()
    }
}