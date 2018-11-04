package cat.devsofthecoast.teammanagementdemo.commons.repository.questions.impl

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.questions.impl.QuestionsServiceImpl

class QuestionsRepositoryImpl : QuestionsRepository {

    private val service = QuestionsServiceImpl()

    override fun getQuestion(key: String, listener: ServiceCallback<Question?>?) {
        service.getQuestion(key, listener)
    }

    override fun getAllQuestions(listener: ServiceCallback<ArrayList<Question>?>?) {
        service.getAllQuestions(listener)
    }

    override fun setQuestions(questions: List<Question>, listener: ServiceCallback<Boolean>?) {
        service.setNewQuestions(questions, listener)
    }

    override fun clearChild(child: String, serviceCallback: ServiceCallback<Boolean>) {
        service.clearDatabaseChild(child, serviceCallback)
    }

    override fun assignKey(databaseModel: DatabaseModel) {
        service.assignNewKey(databaseModel)
    }

    override fun assignKeys(databaseModels: List<DatabaseModel>) {
        for (databaseModel in databaseModels){
            assignKey(databaseModel)
        }
    }

}