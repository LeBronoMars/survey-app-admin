package avinnovz.com.surveyadmin.interfaces


/**
 * Created by rsbulanon on 6/3/17.
 */
interface OnApiRequestListener {
    fun onApiRequestBegin(action: String?)
    fun onApiRequestSuccess(action: String?, result: Any)
    fun onApiRequestFailed(action: String?, t: Throwable)
}