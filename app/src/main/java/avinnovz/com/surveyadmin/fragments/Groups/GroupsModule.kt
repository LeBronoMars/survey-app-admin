package avinnovz.com.surveyadmin.fragments.Groups

import avinnovz.com.surveyadmin.dagger.qualifiers.ForActivity
import dagger.Module
import dagger.Provides

/**
 * Created by rsbulanon on 6/4/17.
 */
@Module
class GroupsModule(val view: GroupsContract.View) {

    @Provides
    @ForActivity
    fun provideView() : GroupsContract.View = view
}