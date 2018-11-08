package cat.devsofthecoast.teammanagementdemo.commons.services.teams.impl

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.teams.TeamsService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import android.content.ClipData.Item
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import com.google.firebase.database.GenericTypeIndicator



class TeamsServiceImpl : BaseService(), TeamsService {

    private val TEAMS_LOCATION = "teams"
    override val refTable: DatabaseReference = firebaseDatabase.child(TEAMS_LOCATION)

    override fun setTeams(teams: List<Team>, listener: ServiceCallback<Boolean>?) {
        val keysTeams = mutableMapOf<String, Team>()
        for (team: Team in teams) {
            if(team.key == null) assignNewKey(team)
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

    override fun getTeams(serviceCallback: ServiceCallback<ArrayList<Team>>) {
        getSingleSnapShot(refTable) {
            if(it != null) {
                val teams: ArrayList<Team> = arrayListOf()
                it.children.forEach {
                    teams.add(it.getValue(Team::class.java)!!)
                }
                serviceCallback.onSuccess(teams)
            } else {
                serviceCallback.onError(NullResponseFirebase())
            }
        }
    }
}