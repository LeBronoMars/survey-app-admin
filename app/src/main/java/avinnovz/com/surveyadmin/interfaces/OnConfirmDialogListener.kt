package proto.com.kotlinapp.interfaces

/**
 * Created by rsbulanon on 6/3/17.
 */
interface OnConfirmDialogListener {
    fun onConfirmed(action: String)
    fun onCancelled(action: String)
}