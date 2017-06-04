@file:JvmName("ExtensionsUtils")

package avinnovz.com.surveyadmin.commons

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by rsbulanon on 6/44/17.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImage(imageUrl: String) {
    Picasso.with(context).load(imageUrl).into(this)
}

inline fun <reified T : Parcelable> createParcel(
    crossinline createFromParcel: (Parcel) -> T?) : Parcelable.Creator<T> =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
        override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }


inline fun <reified T : Any> mock(): T = org.mockito.Mockito.mock(T::class.java)

fun sum(a: Int, b:Int) = a + b