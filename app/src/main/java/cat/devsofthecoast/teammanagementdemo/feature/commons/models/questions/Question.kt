package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

abstract class Question {
    abstract val type: QuestionType
    abstract var key: String?
    abstract var statement: String?
    abstract var picture_url: String?
}