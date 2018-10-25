package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.database.TMDCallback
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.database.TMDServiceImpl
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.DummyCreator
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository

class TMDRepositoryImpl: TMDRepository {
    override fun getQuestion(key: String, listener: TMDCallback<Question?, Throwable?>?) {
        tmdService.getQuestion(key, listener)
    }

    private val tmdService = TMDServiceImpl()

    override fun getAllQuestions (listener: TMDCallback<ArrayList<Question>?, Throwable>?) {
        tmdService.getAllQuestions(listener)
    }

    override fun setDummieDatabase(listener: TMDCallback<Boolean, Throwable>?) {
        val questions: List<Question> = DummyCreator.createQuestions()
        tmdService.setNewQuestions(questions, listener)
    }
}