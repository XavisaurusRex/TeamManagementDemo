package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.database

import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.Question

interface TMDService {
    //RELATIVE TO QUESTIONS
    fun setNewQuestion(question: Question, listener: TMDCallback<Boolean, Throwable>?)

    fun setNewQuestions(questions: List<Question>, listener: TMDCallback<Boolean, Throwable>?)
    fun getAllQuestions(listener: TMDCallback<ArrayList<Question>?, Throwable>?)
    fun getQuestion(key: String, listener: TMDCallback<Question?, Throwable>?)
}