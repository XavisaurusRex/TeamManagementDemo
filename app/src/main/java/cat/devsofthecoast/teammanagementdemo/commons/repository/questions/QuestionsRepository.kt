package cat.devsofthecoast.teammanagementdemo.commons.repository.questions

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import java.util.*

interface QuestionsRepository {

    fun setDummieDatabase(listener: ServiceCallback<Boolean>?)

    fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?)

    fun getQuestion(key: String, listener: ServiceCallback<Question?>?)

    fun clearChild(child: String, serviceCallback: ServiceCallback<Boolean>)
}
