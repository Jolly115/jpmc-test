package com.app.a20230125_jollygupta_nycschools.di

import com.app.a20230125_jollygupta_nycschools.remote.DataManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface DataManagerEntryPoint {
    fun getDataManager(): DataManager
}