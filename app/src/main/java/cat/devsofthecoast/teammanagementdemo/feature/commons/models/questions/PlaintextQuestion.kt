package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

class PlaintextQuestion : BaseQuestion<String?>() {
    override val type: QuestionType = QuestionType.TYPE_PLAINTEXT
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    override var questionResponse: String? = null
}