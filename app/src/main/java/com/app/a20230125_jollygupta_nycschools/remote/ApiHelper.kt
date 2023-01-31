package com.app.a20230125_jollygupta_nycschools.remote

import retrofit2.Retrofit
import javax.inject.Inject

class ApiHelper @Inject constructor(private val retrofit: Retrofit) {

    fun getSchoolServices(): SchoolServices {
        return retrofit.create(SchoolServices::class.java)
    }
}