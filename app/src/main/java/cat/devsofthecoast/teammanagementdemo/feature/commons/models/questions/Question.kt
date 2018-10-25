package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

import cat.devsofthecoast.teammanagementdemo.feature.commons.models.DatabaseModel

abstract class Question : DatabaseModel() {
    abstract val type: QuestionType
    abstract var statement: String?
    abstract var picture_url: String?
}