package avinnovz.com.surveyadmin.models.response

import android.os.Parcel
import android.os.Parcelable
import proto.com.kotlinapp.commons.createParcel

/**
 * Created by rsbulanon on 6/4/17.
 */
class BaseResponse(val totalPages: Int, val totalElements: Int,
                   val last: Boolean, val size: Int, val number: Int,
                   val sort: String, val numberOfElements: Int, val first: Boolean) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { BaseResponse(it) }
    }

    constructor(source: Parcel) : this(
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