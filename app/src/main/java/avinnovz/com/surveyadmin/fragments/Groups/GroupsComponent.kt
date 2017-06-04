package avinnovz.com.surveyadmin.fragments.Groups

import avinnovz.com.surveyadmin.dagger.components.AppComponent
import avinnovz.com.surveyadmin.dagger.qualifiers.ForActivity
import dagger.Component

/**
 * Created by rsbulanon on 6/4/17.
 */
@ForActivity
@Component(dependencies = arrayOf(AppComponent::class),
            modules = arrayOf(GroupsModule::class))
interface GroupsComponent {
    fun inject(groupsFragment: GroupsFragment)
}