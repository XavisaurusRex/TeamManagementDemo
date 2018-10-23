package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

class MultipleChoiceQuestion : BaseQuestion<List<Int>>() {
    override val type: QuestionType = QuestionType.TYPE_MULTICHOICE
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    override var questionResponse: List<Int> = listOf()

    var options: List<String> = listOf()
}