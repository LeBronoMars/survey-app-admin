package avinnovz.com.surveyadmin.models.response.Department

import android.os.Parcel
import android.os.Parcelable
import avinnovz.com.surveyadmin.commons.createParcel

/**
 * Created by rsbulanon on 6/4/17.
 */
class Departments(val content: ArrayList<DepartmentData>, val totalPages: Int, val totalElements: Int,
                  val last: Boolean, val size: Int, val number: Int,
                  val sort: String, val numberOfElements: Int, val first: Boolean) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { Departments(it) }
    }

    constructor(source: Parcel) : this(
            source.createTypedArrayList(DepartmentData.CREATOR),
            source.readInt(),
            source.readInt(),
            1 == source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(content)
        dest.writeInt(totalPages)
        dest.writeInt(totalElements)
        dest.writeInt((if (last) 1 else 0))
        dest.writeInt(size)
        dest.writeInt(number)
        dest.writeString(sort)
        dest.writeInt(numberOfElements)
        dest.writeInt((if (first) 1 else 0))
    }
}