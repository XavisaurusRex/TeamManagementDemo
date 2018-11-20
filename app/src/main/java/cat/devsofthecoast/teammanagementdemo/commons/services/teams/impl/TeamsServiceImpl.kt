package cat.devsofthecoast.teammanagementdemo.commons.services.teams.impl

import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.teams.TeamsService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference


class TeamsServiceImpl : BaseService(), TeamsService {

    private val TEAMS_LOCATION = "teams"
    override val refTable: DatabaseReference = firebaseDatabase.child(TEAMS_LOCATION)

    override fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?) {
        val keysTeams = mutableMapOf<String, Team>()
        for (team: Team in teams) {
            if (team.key == null) assignNewKey(team)
            keysTeams[team.key!!] = team
        }
        if (keysTeams.isNotEmpty()) {
            addNewDataList(keysTeams, OnCompleteListener {
                when {
                    it.isSuccessful -> {
                        listener?.onSuccess(it.isSuccessful)
                    }
                    it.isCanceled -> listener?.onError(PostingFirebaseException())
                    else -> listener?.onError(PostingFirebaseException())
                }
            })
        } else {
            listener?.onError(PostingFirebaseException())
        }
    }

    override fun setTeam(input: Team, serviceCallback: ServiceCallback<Void?>) {
        addNewData(input, OnCompleteListener {
            when {
                it.isSuccessful -> {
                    serviceCallback.onSuccess(null)
                }
                it.isCanceled -> serviceCallback.onError(PostingFirebaseException())
                else -> serviceCallback.onError(PostingFirebaseException())
            }
        })
    }

    override fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>, teamsCache: TMDCache<String, ArrayList<Team>>) {
        getSingleSnapShot(refTable) {
            if (it != null) {
                val teams: ArrayList<Team> = arrayListOf()
                it.children.forEach {
                    teams.add(it.getValue(Team::class.java)!!)
                }
                teamsCache["ALL"] = teams
                serviceCallback.onSuccess(teams)
            } else {
                serviceCallback.onError(NullResponseFirebase())
            }
        }
    }

    override fun getTeam(key: String, serviceCallback: ServiceCallback<Team>) {
        getSingleSnapShot(refTable.child(key)){
            if (it != null) {
                val team = it.getValue(Team::class.java)!!
                serviceCallback.onSuccess(team)
            } else {
                serviceCallback.onError(NullResponseFirebase())
            }
        }
    }
}