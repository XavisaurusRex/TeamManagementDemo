package cat.devsofthecoast.teammanagementdemo.commons.models

import android.os.Parcel
import android.os.Parcelable

class Team() : DatabaseModel(), Parcelable {
    override var key: String? = null
    var name: String? = null
    var logo_url: String? = null
    var survey: ArrayList<String> = arrayListOf()
    var players: ArrayList<String> = arrayListOf()
    var trainers: ArrayList<String> = arrayListOf()


    constructor(parcel: Parcel) : this() {
        key = parcel.readString()
        name = parcel.readString()
        logo_url = parcel.readString()
        parcel.readList(survey, String::class.java.classLoader)
        parcel.readList(players, String::class.java.classLoader)
        parcel.readList(trainers, String::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(key)
        dest?.writeString(name)
        dest?.writeString(logo_url)
        dest?.writeList(survey)
        dest?.writeList(players)
        dest?.writeList(trainers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}