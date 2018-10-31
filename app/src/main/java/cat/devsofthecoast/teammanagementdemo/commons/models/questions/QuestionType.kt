package cat.devsofthecoast.teammanagementdemo.commons.models.questions

enum class QuestionType(val value: Int) {
    TYPE_BOOLEAN(0),
    TYPE_PLAINTEXT(1),
    TYPE_SINGLECHOICE(2),
    TYPE_MULTICHOICE(3),
    TYPE_NUMERIC(4),
    TYPE_HUMANBODY(5)
}