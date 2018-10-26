package cat.devsofthecoast.teammanagementdemo.commons.repository

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*

object DummyCreator {
    fun createQuestions(): List<Question> {
        val questions = arrayListOf<Question>()

        val booleanQuestion = BooleanQuestion()
        booleanQuestion.statement = "Have you slept well today?"

        val numericQuestion = NumericQuestion()
        numericQuestion.statement = "How many hours have you slept?"

        val plaintextQuestion = PlaintextQuestion()
        plaintextQuestion.statement = "Any observation?"

        val singleChoiceQuestion = SingleChoiceQuestion()
        singleChoiceQuestion.statement = "What is your favorite dish?"
        singleChoiceQuestion.options = arrayListOf("potatoes", "dragon sandwich", "pumpkin cream", "troll meatballs")

        val multipleChoiceQuestion = MultipleChoiceQuestion()
        multipleChoiceQuestion.statement = "Where would you like to go on a trip?"
        multipleChoiceQuestion.options = arrayListOf("Girona", "Barcelona", "Valencia", "Sant Feliu de Guíxols")

        questions.add(booleanQuestion)
        questions.add(numericQuestion)
        questions.add(plaintextQuestion)
        questions.add(singleChoiceQuestion)
        questions.add(multipleChoiceQuestion)

        return questions
    }
}
