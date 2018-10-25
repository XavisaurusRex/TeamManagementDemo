package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.feature.commons.databasefactory.DatabaseFactory
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.*
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.DummyCreator
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TMDRepositoryImpl(private val service: TMDService) : TMDRepository {
    private val databaseFactory = DatabaseFactory()

    override fun setDummieDatabase() {
        val questions: List<Question> = DummyCreator.createQuestions()


        databaseFactory.setNewQuestions(questions)
        for (question: Question in questions) {
            databaseFactory.setNewQuestion(question) { success: Boolean, exception: Exception? ->
                if (!success) {
                    throw exception!!
                }
            }
        }
    }

    override fun getTeams(): List<Team>? =
            service.getTeams().execute().body()
}