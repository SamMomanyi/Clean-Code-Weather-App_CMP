package org.example.project.di

import org.example.project.core.data.HttpClientFactory
import org.example.project.weather.data.Network.KtorRemoteWeatherDataSource
import org.example.project.weather.data.Network.RemoteWeatherDataSource
import org.example.project.weather.data.repository.DefaultPlaceRepository
import org.example.project.weather.data.repository.DefaultWeatherRepository
import org.example.project.weather.domain.PlaceRepository
import org.example.project.weather.domain.WeatherRepository
import org.example.project.weather.presentation.weatherInfo.WeatherInfoViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module {
    single {  HttpClientFactory.create(get () )  }
    singleOf(::KtorRemoteWeatherDataSource).bind<RemoteWeatherDataSource>()
    singleOf(::DefaultWeatherRepository).bind<WeatherRepository>()
    singleOf(::DefaultPlaceRepository).bind<PlaceRepository>()

//    single {
//        get<DatabaseFactory>()
//            .create()
//            .setDriver(BundledSQLiteDriver())
//            .build()
//    }
//    single{
//        get<FavoriteBookDatabase>().favoriteBookDao
//    }

    viewModelOf(::WeatherInfoViewModel)



}
