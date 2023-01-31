package com.app.a20230125_jollygupta_nycschools.remote

import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolServices {

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolList(): Response<ArrayList<SchoolDetail>>

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetail(): Response<ArrayList<SchoolDetail>>
}