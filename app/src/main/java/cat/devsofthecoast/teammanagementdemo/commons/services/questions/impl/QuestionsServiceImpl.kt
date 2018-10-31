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

class QuestionsServiceImpl : BaseService(), QuestionsService {


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
        getSingleSnapShot(refQuestions.child(key)) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                listener?.onSuccess(getQuestion(dataSnapshot))
            } else {
                listener?.onError(NullResponseFirebase())
            }
        }
    }

    override fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?) {
        getSingleSnapShot(refQuestions) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                listener?.onSuccess(getQuestions(dataSnapshot))
            } else {
                listener?.onError(NullResponseFirebase())
            }
        }
    }


    override fun setNewQuestion(question: Question, listener: ServiceCallback<Boolean>?) {
        question.assignNewKey(refQuestions)
        addNewData(refQuestions, question, OnCompleteListener {
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
            question.assignNewKey(refQuestions)
            keysQuestions[question.key!!] = question
        }
        if (keysQuestions.isNotEmpty()) {
            addNewDataList(refQuestions, keysQuestions, OnCompleteListener {
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
        removeAll(refQuestions,OnCompleteListener {
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