package com.app.a20230125_jollygupta_nycschools.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.a20230125_jollygupta_nycschools.common.Utils.dismissProgressDialog
import com.app.a20230125_jollygupta_nycschools.common.Utils.showProgressDialog
import com.app.a20230125_jollygupta_nycschools.databinding.FragmentHomeBinding
import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import com.app.a20230125_jollygupta_nycschools.ui.home.adapter.SchoolAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment(),HomeNavigator {
    private lateinit var binding:FragmentHomeBinding
    private val homeFragmentViewModel: HomeViewModel by activityViewModels()
    private  var schoolList :ArrayList<SchoolDetail> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        showProgressDialog(requireActivity())
        homeFragmentViewModel.getSchoolListData()
        initialLiveData()
        return binding.root
    }
    private fun initialLiveData() {
        homeFragmentViewModel.schoolListLiveData.observe(viewLifecycleOwner){
            schoolList.addAll(homeFragmentViewModel.schoolListLiveData.value!!)
            setupAdapter(schoolList)
            Log.e(TAG, "ok api called: ", )
            setupSearch()
        }
    }

    private fun setupSearch() {
        //Add listener when searching
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, k: Int, i1: Int, i2: Int) {
                val searchList: ArrayList<SchoolDetail> = arrayListOf<SchoolDetail>()
                for (i in schoolList.indices) {
                    if (schoolList[i].school_name!!.toLowerCase().contains(
                            charSequence.toString().lowercase(
                                Locale.getDefault()
                            )
                        )
                    ) {
                        searchList.add(schoolList[i])
                    }
                    binding.rvList.apply {
                        layoutManager = LinearLayoutManager(requireActivity(),
                            RecyclerView.VERTICAL, false)
                        val adaper = SchoolAdapter(requireActivity(),searchList)
                        adaper.setItem(searchList)
                        adaper.listner = this@HomeFragment
                        adapter = adaper
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun setupAdapter(list:ArrayList<SchoolDetail>) {

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireActivity(),
                RecyclerView.VERTICAL, false)
            val adaper = SchoolAdapter(requireActivity(),list)
            adaper.setItem(list)
            adaper.listner = this@HomeFragment
            adapter = adaper
        }
    }

    override fun onDetailClick(data:SchoolDetail) {
        val myData:String = Gson().toJson(data)
        val navigationAction = HomeFragmentDirections.actionToDetailFragment(myData)
        findNavController().navigate(navigationAction)
    }
}