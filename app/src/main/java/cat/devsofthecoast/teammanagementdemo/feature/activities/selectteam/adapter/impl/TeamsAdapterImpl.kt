package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter.TeamsAdapter
import kotlinx.android.synthetic.main.item_team_view.view.*
import java.util.*

class TeamsAdapterImpl(
        private val context: Context,
        private val teams: ArrayList<Team>) : RecyclerView.Adapter<TeamsAdapterImpl.ViewHolder>(), TeamsAdapter {
    private var selectedView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsAdapterImpl.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_team_view, parent, false)
        view.setOnClickListener {
            if (it.background.level == 0) {
                it.background.level = 1
                selectedView?.background?.level = 0
                selectedView = it
            }
        }
        return TeamsAdapterImpl.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsAdapterImpl.ViewHolder, position: Int) {
        holder.itemView.tvName.text = teams[position].name
        holder.itemView.tag = teams[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getSelectedTeam(): Team? {
        return selectedView?.tag as Team?
    }

    override fun add(team: Team) {
        this.teams.add(team)
        this.notifyItemChanged(teams.size - 1)
    }

    override fun add(teams: List<Team>) {
        this.teams.addAll(teams)
        this.notifyItemRangeInserted(this.teams.size - teams.size, this.teams.size)
    }

    override fun remove(team: Team) {
        this.teams.add(team)
        this.notifyItemChanged(teams.size - 1)
    }

    override fun removeAt(position: Int) {
        this.teams.removeAt(position)
        this.notifyItemChanged(position)
    }

    override fun size(): Int {
        return teams.size
    }

    override fun removeAll() {
        teams.clear()
        notifyDataSetChanged()
    }
}