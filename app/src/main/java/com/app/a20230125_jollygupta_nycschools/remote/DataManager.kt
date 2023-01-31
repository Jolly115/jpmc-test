package com.app.a20230125_jollygupta_nycschools.remote

import javax.inject.Inject

class DataManager @Inject constructor(private val apiHelper: ApiHelper) {
    fun getSchoolServices(): SchoolServices {
        return apiHelper.getSchoolServices()
    }

}