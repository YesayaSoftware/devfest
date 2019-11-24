package software.yesaya.devfest.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import software.yesaya.devfest.BuildConfig

private const val BASE_URL = "http://10.0.2.2:8080/api/"

class RetrofitBuilder() {
    companion object {
        private val client =
            buildClient()
        private val retrofit =
            buildRetrofit(client)

        private fun buildRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun buildClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var request = chain.request()

                    val builder = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Connection", "close")

                    request = builder.build()

                    chain.proceed(request)
                }

            if (BuildConfig.DEBUG)
                builder.addNetworkInterceptor(StethoInterceptor())

            return builder.build()
        }

        fun <T> createService(service: Class<T>): T {
            return retrofit.create(service)
        }

        fun getRetrofits(): Retrofit {
            return retrofit
        }
    }
}