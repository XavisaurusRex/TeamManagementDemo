package cat.devsofthecoast.teammanagementdemo.commons.repository.database

import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import com.google.firebase.database.*
import java.lang.Error

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

    protected fun addNewDataList(databaseReference: DatabaseReference, databaseModels: Map<String, DatabaseModel>, listener: TMDCallback<Boolean, Throwable>?) {
        databaseReference.updateChildren(databaseModels).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.invoke(it.isSuccessful, null)
                }
                it.isCanceled -> listener?.invoke(it.isSuccessful, PostingFirebaseException())
                else -> listener?.invoke(false, PostingFirebaseException())
            }
        }
    }

    protected fun addNewData(databaseReference: DatabaseReference, databaseModel: DatabaseModel, listener: TMDCallback<Boolean, Throwable>?) {
        databaseReference.child(databaseModel.key!!).setValue(databaseModel).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.invoke(it.isSuccessful, null)
                }
                it.isCanceled -> listener?.invoke(it.isSuccessful, PostingFirebaseException())
                else -> listener?.invoke(false, PostingFirebaseException())
            }
        }
    }
}