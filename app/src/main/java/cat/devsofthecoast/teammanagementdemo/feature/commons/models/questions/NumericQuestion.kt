package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

import com.google.firebase.database.Exclude

open class NumericQuestion: BaseQuestion<Int?>() {
    override val type: QuestionType = QuestionType.TYPE_NUMERIC
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    @Exclude
    override var questionResponse: Int? = null
        @Exclude
        get() = field
        @Exclude
        set(value) {field=value}
}