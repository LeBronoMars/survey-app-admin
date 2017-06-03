package avinnovz.com.surveyadmin.activities.Login

import avinnovz.com.surveyadmin.dagger.components.AppComponent
import avinnovz.com.surveyadmin.dagger.qualifiers.ForActivity
import dagger.Component

/**
 * Created by rsbulanon on 6/3/17.
 */
@ForActivity
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}