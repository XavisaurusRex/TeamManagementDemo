package cat.devsofthecoast.teammanagementdemo.commons.models.users

import android.os.Parcel
import android.os.Parcelable

class Trainer() : BaseUser(), Parcelable {
    override var key: String? = null
    override var name: String? = null
    override var surname: String? = null
    override var picture_url: String? = null
    override var email: String? = null
    override var phoneNumber: Int? = null

    var team: String? = null


    //region PARCELABLE

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(key)
        dest?.writeString(name)
        dest?.writeString(surname)
        dest?.writeString(picture_url)
        dest?.writeString(email)
        dest?.writeValue(phoneNumber)
        dest?.writeString(team)
    }

    constructor(parcel: Parcel) : this() {
        key = parcel.readString()
        name = parcel.readString()
        surname = parcel.readString()
        picture_url = parcel.readString()
        email = parcel.readString()
        phoneNumber = parcel.readValue(Int::class.java.classLoader) as? Int
        team = parcel.readString()
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trainer> {
        override fun createFromParcel(parcel: Parcel): Trainer {
            return Trainer(parcel)
        }

        override fun newArray(size: Int): Array<Trainer?> {
            return arrayOfNulls(size)
        }
    }

    //endregion
}