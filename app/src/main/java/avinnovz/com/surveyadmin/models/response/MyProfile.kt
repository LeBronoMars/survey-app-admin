package avinnovz.com.surveyadmin.models.response

import android.os.Parcel
import android.os.Parcelable
import avinnovz.com.surveyadmin.commons.createParcel
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by rsbulanon on 6/4/17.
 */
class MyProfile(val id: String,
                val active: Boolean,
                val address: String,
                val email: String,
                val gender: String,
                val role: String,
                val synced: Boolean,
                val username: String,
                @SerializedName("created_at") val createdAt: Date,
                @SerializedName("employee_no") val employeeNo: String,
                @SerializedName("first_name") val firstName: String,
                @SerializedName("middle_name") val middleName: String,
                @SerializedName("last_name") val lastName: String,
                @SerializedName("is_synced") val isSynced: Boolean,
                @SerializedName("pic_url") val picUrl: String,
                @SerializedName("contact_no") val contactNo: String,
                @SerializedName("updated_at") val updatedAt: Date) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { MyProfile(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readSerializable() as Date,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeInt((if (active) 1 else 0))
        dest.writeString(address)
        dest.writeString(email)
        dest.writeString(gender)
        dest.writeString(role)
        dest.writeInt((if (synced) 1 else 0))
        dest.writeString(username)
        dest.writeSerializable(createdAt)
        dest.writeString(employeeNo)
        dest.writeString(firstName)
        dest.writeString(middleName)
        dest.writeString(lastName)
        dest.writeInt((if (isSynced) 1 else 0))
        dest.writeString(picUrl)
        dest.writeString(contactNo)
        dest.writeSerializable(updatedAt)
    }
}