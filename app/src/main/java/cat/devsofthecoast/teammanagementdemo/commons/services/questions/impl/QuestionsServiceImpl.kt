package cat.devsofthecoast.teammanagementdemo.commons.services.questions.impl

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.QuestionFactory
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.QuestionType
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.questions.QuestionsService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference

class QuestionsServiceImpl : BaseService(), QuestionsService {
    private val QUESTIONS_LOCATION = "questions"
    override val refTable: DatabaseReference = firebaseDatabase.child(QUESTIONS_LOCATION)

    //region QUESTIONS

    private fun getQuestions(dataSnapshot: DataSnapshot): ArrayList<Question> {
        val questions: ArrayList<Question> = arrayListOf()
        dataSnapshot.children.forEach {
            questions.add(getQuestion(it))
        }
        return questions
    }

    private fun getQuestion(dataSnapshot: DataSnapshot): Question {
        val questionType = dataSnapshot.child("type").getValue(QuestionType::class.java)
        return dataSnapshot.getValue(QuestionFactory.getClass(questionType!!)) as Question
    }

    override fun getQuestion(key: String, listener: ServiceCallback<Question?>?) {
        getSingleSnapShot(refTable.child(key)) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                listener?.onSuccess(getQuestion(dataSnapshot))
            } else {
                listener?.onError(NullResponseFirebase())
            }
        }
    }

    override fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?) {
        getSingleSnapShot(refTable) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                listener?.onSuccess(getQuestions(dataSnapshot))
            } else {
                listener?.onError(NullResponseFirebase())
            }
        }
    }


    override fun setNewQuestion(question: Question, listener: ServiceCallback<Boolean>?) {
        if(question.key == null) assignNewKey(question)
        addNewData(question, OnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.onSuccess(it.isSuccessful)
                }
                it.isCanceled -> listener?.onError(PostingFirebaseException())
                else -> listener?.onError(PostingFirebaseException())
            }
        })
    }

    override fun setNewQuestions(questions: List<Question>, listener: ServiceCallback<Boolean>?) {
        val keysQuestions = mutableMapOf<String, Question>()
        for (question: Question in questions) {
            if(question.key == null) assignNewKey(question)
            keysQuestions[question.key!!] = question
        }
        if (keysQuestions.isNotEmpty()) {
            addNewDataList(keysQuestions, OnCompleteListener {
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

    override fun clearDatabaseChild(child: String, listener: ServiceCallback<Boolean>?) {
        removeAll(OnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.onSuccess(it.isSuccessful)
                }
                it.isCanceled -> listener?.onError(PostingFirebaseException())
                else -> listener?.onError(PostingFirebaseException())
            }
        } )
    }
}