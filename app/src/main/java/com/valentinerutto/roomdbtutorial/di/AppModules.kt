package com.valentinerutto.roomdbtutorial.di

import com.valentinerutto.roomdbtutorial.App
import com.valentinerutto.roomdbtutorial.data.LineRepository
import com.valentinerutto.roomdbtutorial.data.local.PickUpLIneDatabase
import com.valentinerutto.roomdbtutorial.data.remote.network.ApiService
import com.valentinerutto.roomdbtutorial.data.remote.network.RetrofitClient.createOkClient
import com.valentinerutto.roomdbtutorial.data.remote.network.RetrofitClient.createRetrofit
import com.valentinerutto.roomdbtutorial.ui.LineViewModel
import com.valentinerutto.roomdbtutorial.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit


val networkingModule = module {
    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(
            baseUrl = Constants.BASE_URL, get()
        )
    }
}
val repositoryModule = module {
    single { LineRepository(get(), database().lineDao()) }
}
val viewmodelModule = module {

    viewModel { LineViewModel(get()) }

}

val databaseModule = module {

    single { PickUpLIneDatabase.getDatabase(context = androidContext()) }


}

fun Scope.database() = get<PickUpLIneDatabase>()

