package cat.devsofthecoast.teammanagementdemo.commons.repository.questions

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import org.json.JSONArray
import java.util.*

interface QuestionsRepository {

    fun setDummieDatabase(statements: JSONArray, responseOptions: JSONArray, listener: ServiceCallback<Boolean>?)

    fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?)

    fun getQuestion(key: String, listener: ServiceCallback<Question?>?)

    fun clearChild(child: String, serviceCallback: ServiceCallback<Boolean>)
}
