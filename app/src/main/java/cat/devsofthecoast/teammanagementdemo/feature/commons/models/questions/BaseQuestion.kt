package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

abstract class BaseQuestion<T> : Question() {
    abstract var questionResponse: T
}