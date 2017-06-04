package avinnovz.com.surveyadmin.dagger.modules

import android.app.Application
import android.content.Context
import avinnovz.com.surveyadmin.BuildConfig
import avinnovz.com.surveyadmin.interfaces.ApiInterface
import avinnovz.com.surveyadmin.models.others.MyProfileManager
import avinnovz.com.surveyadmin.models.others.TokenManager
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rsbulanon on 6/3/17.
 */
@Module
class RetrofitModule(val app: Application) {

    private val CONNECTION_TIME_OUT = 60
    private val READ_TIME_OUT = 60

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        /** initialize ok http client  */
        var cacheDir = application.externalCacheDir
        if (cacheDir == null) {
            cacheDir = application.cacheDir
        }
        val cache = Cache(cacheDir, (10 * 1024 * 1024).toLong())

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .connectTimeout(CONNECTION_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .build()
        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.HOST_NAME)
                .client(provideOkHttpClient(app))
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return provideRetrofit().create<ApiInterface>(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideTokenManager() : TokenManager {
        return TokenManager()
    }

    @Provides
    @Singleton
    fun provideMyProfileManager() : MyProfileManager {
        return MyProfileManager()
    }

}