package cat.devsofthecoast.teammanagementdemo.commons.repository

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*
import java.util.*

object DummyCreator {
    fun createQuestions(): List<Question> {
        val r = Random()
        val questions = arrayListOf<Question>()

        val booleanQuestion = BooleanQuestion()
        booleanQuestion.statement = "Have you slept well today?"

        val numericQuestion = NumericQuestion()
        numericQuestion.statement = "How many hours have you slept?"
        numericQuestion.max = r.nextInt(50)
        numericQuestion.min = r.nextInt(numericQuestion.max)

        val plaintextQuestion = PlaintextQuestion()
        plaintextQuestion.statement = "Any observation?"

        val singleChoiceQuestion = SingleChoiceQuestion()
        singleChoiceQuestion.statement = "What is your favorite dish?"
        singleChoiceQuestion.options = arrayListOf("potatoes", "dragon sandwich", "pumpkin cream", "troll meatballs",
                "potatoes", "dragon sandwich", "pumpkin cream", "troll meatballs")
        singleChoiceQuestion.options = singleChoiceQuestion.options.subList(0, r.nextInt(singleChoiceQuestion.options.size-2)+2)

        val multipleChoiceQuestion = MultipleChoiceQuestion()
        multipleChoiceQuestion.statement = "Where would you like to go on a trip?"
        multipleChoiceQuestion.options = arrayListOf("Girona", "Barcelona", "Valencia", "Sant Feliu de Guíxols","Girona", "Barcelona", "Valencia", "Sant Feliu de Guíxols","Girona", "Barcelona", "Valencia", "Sant Feliu de Guíxols")
        multipleChoiceQuestion.options = multipleChoiceQuestion.options.subList(0, r.nextInt(multipleChoiceQuestion.options.size-2)+2)

        questions.add(booleanQuestion)
        questions.add(numericQuestion)
        questions.add(plaintextQuestion)
        questions.add(singleChoiceQuestion)
        questions.add(multipleChoiceQuestion)

        return questions
    }
}
