package cat.devsofthecoast.teammanagementdemo.commons.repository.questions

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import org.json.JSONArray
import java.util.*

interface QuestionsRepository {

    fun setQuestions(questions: List<Question>, listener: ServiceCallback<Boolean>?)
    fun getQuestion(key: String, listener: ServiceCallback<Question?>?)

    fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?)
    fun clearChild(child: String, serviceCallback: ServiceCallback<Boolean>)
}
