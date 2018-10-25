package cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions

import java.lang.Exception

class PostingFirebaseException : Exception() {
    override val message: String = "General Error posting data"
}
class PostingFirebaseTaskCanceled : Exception(){
    override val message: String = "Task Posting cancelled before finish"
}
class NullResponseFirebase : Exception(){
    override val message: String = "Database returns null element"
}
class EmptyListResponseFirebase : Exception(){
    override val message: String = "Database returns empty list of items"
}