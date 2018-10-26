package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

open class PlaintextQuestion : BaseQuestion<String?>() {
    override val type: QuestionType = QuestionType.TYPE_PLAINTEXT
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    @Exclude
    override var questionResponse: String? = null
        @Exclude
        get() = field
        @Exclude
        set(value) {field=value}
}
