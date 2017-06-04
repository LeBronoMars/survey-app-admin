package proto.com.kotlinapp.models.response

import android.os.Parcel
import android.os.Parcelable
import avinnovz.com.surveyadmin.commons.createParcel

/**
 * Created by rsbulanon on 6/3/17.
 */
data class LoginResponse(val token: String) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { LoginResponse(it) }
    }

    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(token)
    }
}