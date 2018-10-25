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
    var firebaseDatabase = FirebaseDatabase.getInstance().reference

    override fun setDummieDatabase() {
        val questions: List<Question> = DummyCreator.createQuestions()
        for (question: Question in questions) {
            databaseFactory.setNewQuestion(question) { success: Boolean, exception: Exception? ->
                if (!success) {
                    throw exception!!
                }
            }
        }
    }

    override fun fillDatabase(): Boolean {


        //region questions and surveys

        val survey1: ArrayList<String?> = arrayListOf()
        val survey2: ArrayList<String?> = arrayListOf()

        val questions = arrayListOf<Question>()

        val booleanQuestion = BooleanQuestion()
        booleanQuestion.key = firebaseDatabase.child("questions").push().key
        booleanQuestion.statement = "Have you slept well today?"

        val numericQuestion = NumericQuestion()
        numericQuestion.key = firebaseDatabase.child("questions").push().key
        numericQuestion.statement = "How many hours have you slept?"

        val plaintextQuestion = PlaintextQuestion()
        plaintextQuestion.key = firebaseDatabase.child("questions").push().key
        plaintextQuestion.statement = "Any observation?"

        val singleChoiceQuestion = SingleChoiceQuestion()
        singleChoiceQuestion.key = firebaseDatabase.child("questions").push().key
        singleChoiceQuestion.statement = "What is your favorite dish?"
        singleChoiceQuestion.options = arrayListOf("potatoes", "dragon sandwich", "pumpkin cream", "troll meatballs")

        val multipleChoiceQuestion = MultipleChoiceQuestion()
        multipleChoiceQuestion.key = firebaseDatabase.child("questions").push().key
        multipleChoiceQuestion.statement = "Where would you like to go on a trip?"
        multipleChoiceQuestion.options = arrayListOf("Girona", "Barcelona", "Valencia", "Sant Feliu de Guíxols")

        questions.add(booleanQuestion)
        questions.add(numericQuestion)
        questions.add(plaintextQuestion)
        questions.add(singleChoiceQuestion)
        questions.add(multipleChoiceQuestion)

        survey1.add(booleanQuestion.key)
        survey1.add(numericQuestion.key)
        survey1.add(plaintextQuestion.key)
        survey1.add(singleChoiceQuestion.key)
        survey1.add(multipleChoiceQuestion.key)

        survey2.add(multipleChoiceQuestion.key)
        survey2.add(singleChoiceQuestion.key)
        survey2.add(plaintextQuestion.key)
        survey2.add(numericQuestion.key)
        survey2.add(booleanQuestion.key)

        //endregion

        for (question: Question in questions) {
            firebaseDatabase.child("questions").child(question.key!!).setValue(question) { databaseError: DatabaseError?, databaseReference: DatabaseReference ->
                Log.d(this.javaClass.name,
                        "Question \n" +
                                "  key       -> ${question.key} \n" +
                                "  statement -> ${question.statement} \n" +
                                "  Added correctly to bdd")
            }
        }


        return true
    }

    override fun getTeams(): List<Team>? =
            service.getTeams().execute().body()
}