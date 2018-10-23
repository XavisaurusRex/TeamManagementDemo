package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

class SingleChoiceQuestion : BaseQuestion<Int?>() {
    override val type: QuestionType = QuestionType.TYPE_SINGLECHOICE
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    override var questionResponse: Int? = null

    var options: List<String> = listOf()
}