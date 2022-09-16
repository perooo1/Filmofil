package com.plenart.newfilmofil.di

import com.plenart.newfilmofil.api.TMDBApi
import com.plenart.newfilmofil.presentation.MovieDetailsViewModel
import com.plenart.newfilmofil.presentation.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideOkHttpClient(): OkHttpClient {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDBApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun provideTMDBApi(retrofit: Retrofit): TMDBApi {
        return retrofit.create(TMDBApi::class.java)
    }

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideTMDBApi(get()) }


}

val viewModelModule = module {
    viewModel{ MoviesViewModel() }
//    viewModel { MovieDetailsViewModel() }
    viewModel { parametersHolder -> MovieDetailsViewModel(args = parametersHolder.get())   }

}