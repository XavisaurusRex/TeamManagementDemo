package cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

abstract class BaseQuestion<T> : Question() {
    abstract var questionResponse: T?
}