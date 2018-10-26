package cat.devsofthecoast.teammanagementdemo.commons.repository.questions.impl

import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.questions.QuestionsServiceImpl
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.DummyCreator
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository

class QuestionsRepositoryImpl: QuestionsRepository {
    private val service = QuestionsServiceImpl()


    override fun getQuestion(key: String, listener: ServiceCallback<Question?>?) {
        service.getQuestion(key, listener)
    }

    override fun getAllQuestions (listener: ServiceCallback<ArrayList<Question>?>?) {
        service.getAllQuestions(listener)
    }

    override fun setDummieDatabase(listener: ServiceCallback<Boolean>?) {
        val questions: List<Question> = DummyCreator.createQuestions()
        service.setNewQuestions(questions, listener)
    }
}