package cat.devsofthecoast.teammanagementdemo.feature.commons.databasefactory

import cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.Question
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatabaseFactory {
    private val firebaseDatabase = FirebaseDatabase.getInstance().reference

    val refQuestions = firebaseDatabase.child("questions")
    val refTeams = firebaseDatabase.child("teams")
    val refPlayers = firebaseDatabase.child("players")
    val refTrainers = firebaseDatabase.child("trainers")
    val refEntries = firebaseDatabase.child("entries")

    private fun getNewKey(reference: DatabaseReference): String? {
        return reference.push().key
    }

    private fun DatabaseModel.asginNewKey(reference: DatabaseReference) {
        this.key = getNewKey(reference)
    }

    private fun DatabaseReference.addNewData(databaseModel: DatabaseModel,  listener: ((Boolean, Exception?) -> Unit)?) {
        this.child(databaseModel.key!!).setValue(databaseModel).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.invoke(it.isSuccessful, null)
                }
                it.isCanceled -> listener?.invoke(it.isSuccessful, PostingFirebaseException())
                else -> listener?.invoke(false, PostingFirebaseException())
            }
        }
    }

    private fun DatabaseReference.addNewDataList(databaseModels: Map<String, DatabaseModel>, listener: ((Boolean, Exception?) -> Unit)?) {
        this.setValue(databaseModels).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.invoke(it.isSuccessful, null)
                }
                it.isCanceled -> listener?.invoke(it.isSuccessful, PostingFirebaseException())
                else -> listener?.invoke(false, PostingFirebaseException())
            }
        }
    }


    fun setNewQuestion(question: Question, listener: ((Boolean, Exception?) -> Unit)?) {
        question.asginNewKey(refQuestions)
        refQuestions.addNewData(question, listener)
    }

    fun setNewQuestions(questions: List<Question>, listener: ((Boolean, Exception?) -> Unit)?) {
        val keyQuestions: Map<String, Question> = mapOf()
        for (question: Question in questions) {
            question.asginNewKey(refQuestions)
            keyQuestions.plus(Pair(question.key, question))
        }
        if(keyQuestions.isNotEmpty()){
            refQuestions.addNewDataList(keyQuestions, listener)
    }

}