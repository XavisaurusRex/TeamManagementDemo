package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

open class BooleanQuestion : BaseQuestion<Boolean?>() {

    override val type: QuestionType = QuestionType.TYPE_BOOLEAN

    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    @Exclude
    override var questionResponse: Boolean? = null
        @Exclude
        get() = field
        @Exclude
        set(value) {field=value}
}