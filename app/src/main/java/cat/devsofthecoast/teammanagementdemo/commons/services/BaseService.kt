package cat.devsofthecoast.teammanagementdemo.commons.services

import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*

abstract class BaseService {
    private val firebaseDatabase = FirebaseDatabase.getInstance().reference.child(BuildConfig.TEAMMANAGEMENT_BASE_LOCATION)

    private val QUESTIONS_LOCATION = "questions"
    private val PLAYERS_LOCATION = "players"
    private val TRAINERS_LOCATION = "trainers"
    private val TEAMS_LOCATION = "teams"
    private val SURVEYS_LOCATION = "surveys"

    protected val refQuestions = firebaseDatabase.child(QUESTIONS_LOCATION)
    protected val refPlayers = firebaseDatabase.child(PLAYERS_LOCATION)
    protected val refTrainers = firebaseDatabase.child(TRAINERS_LOCATION)
    protected val refTeams = firebaseDatabase.child(TEAMS_LOCATION)
    protected val refSurveys = firebaseDatabase.child(SURVEYS_LOCATION)


    private fun DatabaseReference.getNewKey(): String {
        return this.push().key!!
    }

    protected fun DatabaseModel.assignNewKey(reference: DatabaseReference) {
        this.key = reference.getNewKey()
    }

    protected fun getSingleSnapShot(databaseReference: DatabaseReference, listener: ((DataSnapshot?) -> Unit)){
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listener.invoke(dataSnapshot)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
                listener.invoke(null)
            }
        })
    }

    protected fun addNewDataList(databaseReference: DatabaseReference, databaseModels: Map<String, DatabaseModel>, onCompleteListener: OnCompleteListener<Void>) {
        databaseReference.updateChildren(databaseModels).addOnCompleteListener(onCompleteListener)
    }

    protected fun addNewData(databaseReference: DatabaseReference, databaseModel: DatabaseModel, onCompleteListener: OnCompleteListener<Void>) {
        databaseReference.child(databaseModel.key!!).setValue(databaseModel).addOnCompleteListener(onCompleteListener)
    }
}