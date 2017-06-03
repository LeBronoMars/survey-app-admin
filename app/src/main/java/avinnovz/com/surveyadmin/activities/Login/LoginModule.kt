package avinnovz.com.surveyadmin.activities.Login

import avinnovz.com.surveyadmin.dagger.qualifiers.ForActivity
import dagger.Module
import dagger.Provides

/**
 * Created by rsbulanon on 6/3/17.
 */
@Module
class LoginModule {

    private var view: LoginContract.View? = null

    constructor(view: LoginContract.View) {
        this.view = view
    }

    @Provides
    @ForActivity
    fun provideView() : LoginContract.View = view!!

}