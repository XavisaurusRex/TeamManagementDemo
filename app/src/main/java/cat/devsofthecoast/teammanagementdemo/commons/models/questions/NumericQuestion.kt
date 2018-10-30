package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

open class NumericQuestion : BaseQuestion<Int>() {
    companion object {
        val DEFAULT_MAX_VALUE = 10
        val DEFAULT_MIN_VALUE = 1
    }

    override val type: QuestionType = QuestionType.TYPE_NUMERIC
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    var min: Int = DEFAULT_MIN_VALUE
    var max: Int = DEFAULT_MAX_VALUE

    @Exclude
    override var questionResponse: Int = 1
        @Exclude
        get() = field
        @Exclude
        set(value) {
            field = value
        }
}