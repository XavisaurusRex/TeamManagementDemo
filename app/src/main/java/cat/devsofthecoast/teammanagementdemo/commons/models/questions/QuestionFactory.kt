package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.QuestionType.*

object QuestionFactory {
    fun getClass(questionType: QuestionType): Class<*> {
        return when (questionType) {
            TYPE_BOOLEAN -> BooleanQuestion::class.java
            TYPE_PLAINTEXT -> PlaintextQuestion::class.java
            TYPE_SINGLECHOICE -> SingleChoiceQuestion::class.java
            TYPE_MULTICHOICE -> MultipleChoiceQuestion::class.java
            TYPE_NUMERIC -> NumericQuestion::class.java
            TYPE_HUMANBODY -> HumanBodyQuestion::class.java
        }
    }
}