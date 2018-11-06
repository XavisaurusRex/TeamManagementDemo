package cat.devsofthecoast.teammanagementdemo.commons.exceptions

import java.lang.Exception

class PostingFirebaseException : Throwable() {
    override val message: String = "General Error posting data"
}

class PostingFirebaseTaskCanceled : Throwable() {
    override val message: String = "Task Posting cancelled before finish"
}

class NullResponseFirebase : Throwable() {
    override val message: String = "Database returns null element"
}

class EmptyListResponseFirebase : Throwable() {
    override val message: String = "Database returns empty list of items"
}