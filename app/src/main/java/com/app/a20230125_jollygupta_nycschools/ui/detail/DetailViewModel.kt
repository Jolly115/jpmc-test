package com.app.a20230125_jollygupta_nycschools.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.a20230125_jollygupta_nycschools.common.Utils
import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import com.app.a20230125_jollygupta_nycschools.remote.DataManager
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel   @Inject constructor(application: Application, private val dataManager: DataManager): AndroidViewModel(application) {
    private val schoolServices = dataManager.getSchoolServices()
    val schoolDetailLiveData: MutableLiveData<SchoolDetail> by lazy {
        MutableLiveData(SchoolDetail())
    }

    fun getSchoolListData(data:SchoolDetail){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val res = schoolServices.getSchoolDetail()
                if(res.isSuccessful){
                    res.body()?.forEach {
                        if(data.dbn == it.dbn){
                            Log.e("getSchoolListData: ", Gson().toJson(it) )
                            schoolDetailLiveData.postValue(it)
                    }
                    }
                    Utils.dismissProgressDialog()
                }
            }catch (e:Exception){
                print(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}