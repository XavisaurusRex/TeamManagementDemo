package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

open class MultipleChoiceQuestion : BaseQuestion<List<Int>>() {
    override val type: QuestionType = QuestionType.TYPE_MULTICHOICE
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null

    var options: List<String> = listOf()

    @Exclude
    override var questionResponse: List<Int>? = listOf()
        @Exclude
        get() = field
        @Exclude
        set(value) {field=value}

}