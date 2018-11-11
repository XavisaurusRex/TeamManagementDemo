package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.controllers.recycler.AdapterList
import kotlinx.android.synthetic.main.item_team_view.view.*
import java.util.*

class TeamsAdapter(
        private val context: Context,
        private val teams: ArrayList<Team>) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>(), AdapterList<Team> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsAdapter.ViewHolder {
        return TeamsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team_view, parent, true))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsAdapter.ViewHolder, position: Int) {
        holder.itemView.tvName.text = teams[position].name
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

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