package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

open class BooleanQuestion : BaseQuestion<Boolean?>() {

    override val type: QuestionType = QuestionType.TYPE_BOOLEAN

    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    override var questionResponse: Boolean? = null
}