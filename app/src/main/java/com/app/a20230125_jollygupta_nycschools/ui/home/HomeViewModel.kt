package com.app.a20230125_jollygupta_nycschools.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import com.app.a20230125_jollygupta_nycschools.remote.DataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(application: Application, private val dataManager: DataManager): AndroidViewModel(application) {
    private val schoolServices = dataManager.getSchoolServices()
    val schoolListLiveData: MutableLiveData<MutableList<SchoolDetail>> by lazy {
        MutableLiveData(mutableListOf())
    }

    fun getSchoolListData(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val res = schoolServices.getSchoolList()
                if(res.isSuccessful){
                    schoolListLiveData.postValue(res.body())
                }
            }catch (e:Exception){
                print(e)
            }
        }
    }
}