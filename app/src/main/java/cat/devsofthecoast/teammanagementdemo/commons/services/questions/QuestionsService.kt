package cat.devsofthecoast.teammanagementdemo.commons.services.questions

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface QuestionsService {
    //RELATIVE TO QUESTIONS
    fun setNewQuestion(question: Question, listener: ServiceCallback<Boolean>?)

    fun setNewQuestions(questions: List<Question>, listener: ServiceCallback<Boolean>?)
    fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?)
    fun getQuestion(key: String, listener: ServiceCallback<Question?>?)
    fun clearDatabaseChild(child: String, listener: ServiceCallback<Boolean>?)

}