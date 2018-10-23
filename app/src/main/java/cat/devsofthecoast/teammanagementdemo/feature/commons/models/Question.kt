package cat.devsofthecoast.teammanagementdemo.feature.commons.models

abstract class Question(val key: String,
                        val type: Int,
                        val statement: String,
                        val picture_url: String) {
}