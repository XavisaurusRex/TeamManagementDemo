package cat.devsofthecoast.teammanagementdemo.feature.commons.repository

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.database.TMDCallback
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.Question

interface TMDRepository {

    fun setDummieDatabase(listener: TMDCallback<Boolean, Throwable?>?)

    // RELATIVE TO QUESTIONS
    fun getAllQuestions(listener: TMDCallback<ArrayList<Question>?, Throwable?>?)

    fun getQuestion(key: String, listener: TMDCallback<Question?, Throwable?>?)
}
