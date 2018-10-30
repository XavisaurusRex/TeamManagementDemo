package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.Exclude

class SingleChoiceQuestion : BaseQuestion<Int>() {
    override val type: QuestionType = QuestionType.TYPE_SINGLECHOICE
    override var key: String? = null
    override var statement: String? = null
    override var picture_url: String? = null
    var options: List<String> = listOf()

    @Exclude
    override var questionResponse: Int = 0
        @Exclude
        get() = field
        @Exclude
        set(value) {
            field = value
        }

}