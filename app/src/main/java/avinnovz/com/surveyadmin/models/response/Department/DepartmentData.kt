package avinnovz.com.surveyadmin.models.response.Department

import android.os.Parcel
import android.os.Parcelable
import avinnovz.com.surveyadmin.commons.createParcel
import avinnovz.com.surveyadmin.models.response.Member
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by rsbulanon on 6/4/17.
 */
class DepartmentData(val id: String, val active: Boolean, val name: String,
                     val description: String, val members: List<Member>,
                     @SerializedName("created_at") val createdAt: Date,
                     @SerializedName("updated_at") val updatedAt: Date) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { DepartmentData(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Member.CREATOR),
            source.readSerializable() as Date,
            source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeInt((if (active) 1 else 0))
        dest.writeString(name)
        dest.writeString(description)
        dest.writeTypedList(members)
        dest.writeSerializable(createdAt)
        dest.writeSerializable(updatedAt)
    }
}