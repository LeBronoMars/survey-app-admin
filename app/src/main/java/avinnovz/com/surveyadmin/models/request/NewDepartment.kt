package avinnovz.com.surveyadmin.models.request

import android.os.Parcel
import android.os.Parcelable
import avinnovz.com.surveyadmin.commons.createParcel

/**
 * Created by rsbulanon on 6/4/17.
 */
class NewDepartment(val name: String, val description: String) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { NewDepartment(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(description)
    }
}