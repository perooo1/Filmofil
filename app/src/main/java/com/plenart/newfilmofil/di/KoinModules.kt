package com.plenart.newfilmofil.di

import android.app.Application
import com.plenart.newfilmofil.api.TMDBApi
import com.plenart.newfilmofil.data.MovieDao
import com.plenart.newfilmofil.data.repository.MovieRepository
import com.plenart.newfilmofil.data.repository.MovieRepositoryImpl
import com.plenart.newfilmofil.data.room.MoviesDatabase
import com.plenart.newfilmofil.presentation.MovieDetailsViewModel
import com.plenart.newfilmofil.presentation.MoviesViewModel
import com.plenart.newfilmofil.presentation.WatchlistViewModel
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

val databaseModule = module {
    fun provideDatabase(app: Application): MoviesDatabase {
        return MoviesDatabase.getDatabase(app)
    }

    fun provideMovieDao(database: MoviesDatabase): MovieDao {
        return database.getMovieDao()
    }

    single<MoviesDatabase> { provideDatabase(get()) }
    single<MovieDao> { provideMovieDao(get()) }

}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel{ MoviesViewModel() }
    viewModel { parametersHolder -> MovieDetailsViewModel(args = parametersHolder.get(), repo = get())   }
    viewModel { WatchlistViewModel(get()) }

}