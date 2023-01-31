package com.app.a20230125_jollygupta_nycschools.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.app.a20230125_jollygupta_nycschools.R
import com.app.a20230125_jollygupta_nycschools.common.Utils
import com.app.a20230125_jollygupta_nycschools.databinding.FragmentDetailBinding
import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(),View.OnClickListener {

    private  var binding: FragmentDetailBinding? =null
    private val detailFragmentViewModel: DetailViewModel by activityViewModels()

    private var model:SchoolDetail?= null
    private var isDetalied = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentDetailBinding.inflate(inflater,container,false)
        Utils.showProgressDialog(requireActivity())
        val args: DetailFragmentArgs by navArgs()
        model = Gson().fromJson(args.data, SchoolDetail::class.java)
        initailLiveData()
        detailFragmentViewModel.getSchoolListData(model!!)
        return binding!!.root

    }
    private fun initailLiveData() {
        detailFragmentViewModel.schoolDetailLiveData.observe(viewLifecycleOwner){
            Log.e( "initailLiveData: ",Gson().toJson(detailFragmentViewModel.schoolDetailLiveData.value) )
            if(detailFragmentViewModel.schoolDetailLiveData.value != null)
            {
                binding!!.llLabel.visibility = View.VISIBLE
                binding!!.llMain.visibility = View.VISIBLE
                model!!.sat_critical_reading_avg_score=detailFragmentViewModel.schoolDetailLiveData.value!!.sat_critical_reading_avg_score
                model!!.sat_math_avg_score=detailFragmentViewModel.schoolDetailLiveData.value!!.sat_math_avg_score
                model!!.num_of_sat_test_takers=detailFragmentViewModel.schoolDetailLiveData.value!!.num_of_sat_test_takers
                model!!.sat_writing_avg_score=detailFragmentViewModel.schoolDetailLiveData.value!!.sat_writing_avg_score
                isDetalied=true
            }
            if(isDetalied){
                binding!!.tvCriticalAvg.text = model!!.sat_critical_reading_avg_score
                binding!!.tvMathAvg.text = model!!.sat_math_avg_score
                binding!!.tvSatTestTakers.text = model!!.num_of_sat_test_takers
                binding!!.tvWritingAvg.text = model!!.sat_writing_avg_score
            }
            else
            {
                binding!!.llLabel.visibility = View.GONE
                binding!!.llMain.visibility = View.GONE
            }
            binding!!.tvSchoolName.text = model?.school_name ?: ""
            binding!!.tvEmail.text = model?.school_email ?: ""
            binding!!.tvLanguage.text = model?.language_classes ?: ""
            binding!!.tvWebsite.text = model?.website ?: ""
            binding!!.tvTotalStudents.text = model?.total_students ?: ""
            binding!!.tvCity.text = model?.city ?: ""
            binding!!.tvAttendance.text = model?.attendance_rate ?: ""
            binding!!.tvEmail.setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tvEmail->{
                onDial()
            }
        }
    }

    private fun onDial() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(model?.school_email!!))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "body")
        startActivity(Intent.createChooser(intent, ""))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        model = null
        binding = null
    }
}