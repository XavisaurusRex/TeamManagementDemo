package cat.devsofthecoast.teammanagementdemo.commons.repository

import cat.devsofthecoast.teammanagementdemo.commons.repository.database.TMDCallback
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import java.util.*

interface TMDRepository {

    fun setDummieDatabase(listener: TMDCallback<Boolean, Throwable?>?)

    // RELATIVE TO QUESTIONS
    fun getAllQuestions(listener: TMDCallback<ArrayList<Question>?, Throwable?>?)

    fun getQuestion(key: String, listener: TMDCallback<Question?, Throwable?>?)
}
