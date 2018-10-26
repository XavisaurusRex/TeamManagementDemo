package cat.devsofthecoast.teammanagementdemo.commons.repository.database

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.QuestionFactory
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.QuestionType
import com.google.firebase.database.DataSnapshot

class TMDServiceImpl : BaseService(), TMDService {


    //region QUESTIONS

    //TODO implement questions as diferents ServiceImplementations
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

    override fun getQuestion(key: String, listener: TMDCallback<Question?, Throwable>?) {
        getSingleSnapShot(refQuestions.child(key)) {
            if (it != null) {
                listener?.invoke(getQuestion(it), null)
            } else {
                listener?.invoke(null, NullResponseFirebase())
            }
        }
    }

    override fun getAllQuestions(listener: TMDCallback<ArrayList<Question>?, Throwable>?) {
        getSingleSnapShot(refQuestions) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                listener?.invoke(getQuestions(dataSnapshot), null)
            } else {
                listener?.invoke(null, NullResponseFirebase())
            }
        }
    }


    override fun setNewQuestion(question: Question, listener: TMDCallback<Boolean, Throwable>?) {
        question.assignNewKey(refQuestions)
        addNewData(refQuestions, question, listener)
    }

    override fun setNewQuestions(questions: List<Question>, listener: TMDCallback<Boolean, Throwable>?) {
        val keyQuestions = mutableMapOf<String, Question>()
        for (question: Question in questions) {
            question.assignNewKey(refQuestions)
            keyQuestions[question.key!!] = question
        }
        if (keyQuestions.isNotEmpty()) {
            addNewDataList(refQuestions, keyQuestions, listener)
        } else {
            listener?.invoke(false, PostingFirebaseException())
        }
    }

    //endregion

}