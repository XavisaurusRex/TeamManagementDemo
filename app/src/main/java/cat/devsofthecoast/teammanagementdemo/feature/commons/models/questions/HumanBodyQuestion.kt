package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

open class HumanBodyQuestion : BaseQuestion<HumanBodyQuestion.HumanBodyResponse>() {
    override val type: QuestionType = QuestionType.TYPE_HUMANBODY
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    override var questionResponse: HumanBodyResponse? = HumanBodyResponse(.0, .0)

    data class HumanBodyResponse(var cordX: Double, var cordY: Double)
}