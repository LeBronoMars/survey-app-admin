package proto.com.kotlinapp.models.response

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by rsbulanon on 6/3/17.
 */
data class LoginResponse(val token: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<LoginResponse> = object : Parcelable.Creator<LoginResponse> {
            override fun createFromParcel(source: Parcel): LoginResponse = LoginResponse(source)
            override fun newArray(size: Int): Array<LoginResponse?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(token)
    }
}