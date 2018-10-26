package cat.devsofthecoast.teammanagementdemo.commons.models.questions

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
abstract class BaseQuestion<T> : Question() {
    abstract var questionResponse: T?
}