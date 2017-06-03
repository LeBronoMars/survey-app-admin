package avinnovz.com.surveyadmin.base

/**
 * Created by rsbulanon on 6/3/17.
 */
interface BaseView {

    fun onShowError(apiAction: String?, header: String, errorMessage: String,
                             positiveText: String, negativeText: String?)

    fun onShowLoading()

    fun onDismissLoading()

    fun onFailedToConnect()

    fun onSocketTimeout()

    fun onServerRelatedError()

    fun onNoConnectionError()
}