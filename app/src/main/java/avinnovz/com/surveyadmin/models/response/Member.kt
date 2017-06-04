package avinnovz.com.surveyadmin.models.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import proto.com.kotlinapp.commons.createParcel

/**
 * Created by rsbulanon on 6/4/17.
 */
class Member(val id: String, val email: String,
             @SerializedName("employee_no") val employeeNo: String,
             @SerializedName("first_name") val firstName: String,
             @SerializedName("middle_name") val middleName: String,
             @SerializedName("last_name") val lastName: String,
             @SerializedName("contact_no") val contactNo: String,
             @SerializedName("pic_url") val picUrl: String) : Parcelable {


    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { Member(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(email)
        dest.writeString(employeeNo)
        dest.writeString(firstName)
        dest.writeString(middleName)
        dest.writeString(lastName)
        dest.writeString(contactNo)
        dest.writeString(picUrl)
    }
}