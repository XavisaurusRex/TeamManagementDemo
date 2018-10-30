package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.adapter

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question

interface QuestionsAdapter {
    fun add(question: Question)
    fun add(questions: List<Question>)

    fun remove(question: Question)
    fun removeAt(position: Int)

    fun size(): Int
    fun removeAll()
}