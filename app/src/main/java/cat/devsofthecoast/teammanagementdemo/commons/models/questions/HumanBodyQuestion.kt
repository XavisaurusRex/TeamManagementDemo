package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

open class HumanBodyQuestion : BaseQuestion<HumanBodyQuestion.HumanBodyResponse>() {
    override val type: QuestionType = QuestionType.TYPE_HUMANBODY
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    @Exclude
    override var questionResponse: HumanBodyResponse? = HumanBodyResponse(.0, .0)
        @Exclude
        get() = field
        @Exclude
        set(value) {field=value}
    data class HumanBodyResponse(var cordX: Double, var cordY: Double)
}