package com.app.a20230125_jollygupta_nycschools.model

data class SchoolDetail(

    //use this message variable if there is an error
    val message:String? = null,
    val  school_name:String?=null,
    val  phone_number:String?=null,
    val  overview_paragraph:String?=null,
    val  school_email:String?=null,
    val  website:String?=null,
    var num_of_sat_test_takers:String?=null,
    var sat_critical_reading_avg_score:String?=null,
    var sat_math_avg_score:String?=null,
    var sat_writing_avg_score:String?=null,
    val  dbn:String?=null,
    val  attendance_rate:String?=null,
    val  school_sports:String?=null,
    val  total_students:String?=null,
    var city:String?=null,
    val  language_classes:String?=null,
    val  location:String?=null
)
