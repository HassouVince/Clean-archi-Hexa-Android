package fr.systemathicdev.consoapp

import android.content.Context
import dagger.Module
import dagger.Provides
import fr.systemathicdev.commons.*
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.math.log

@Module()
class ApplicationModule(val app: ConsoApplication) {

    @Provides
    fun provideContext() : Context = app

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logger
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): ApiProvider<Retrofit> {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.DEFAULT_API_URL)
            .build()
            .let {
                object :ApiProvider<Retrofit>{
                    override fun client() = it
                }
            }
    }

    companion object{
        @Provides
        @Singleton
        fun provideInputAdapterScope() : InputAdapterScope {
            return InputAdapterScope(Dispatchers.IO)
        }

        @Provides
        @Singleton
        fun provideOutputAdapterScopeMain() : OutputAdapterScopeMain {
            return OutputAdapterScopeMain(Dispatchers.Main)
        }

        @Provides
        @Singleton
        fun provideOutputAdapterScopeWorker() : OutputAdapterScopeWorker {
            return OutputAdapterScopeWorker(Dispatchers.IO)
        }
    }
}