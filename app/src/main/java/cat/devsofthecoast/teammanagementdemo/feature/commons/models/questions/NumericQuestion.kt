package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

class NumericQuestion: BaseQuestion<Int?>() {
    override val type: QuestionType = QuestionType.TYPE_NUMERIC
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    override var questionResponse: Int? = null
}