package com.gystry.frameweathertry.ui.area

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gystry.frameweathertry.R
import com.gystry.frameweathertry.databinding.ChooseAreaBindingImpl
import com.gystry.frameweathertry.utils.InjectorUtil

class ChooseAreaFragment : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this, InjectorUtil.getChooseAreaModelFactory()).get(ChooseAreaViewModel::class.java) }
    private var progressDialog:ProgressDialog?=null
    private lateinit var adapter:ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.choose_area, container, false)
        val binding = DataBindingUtil.bind<ChooseAreaBindingImpl>(view)
        binding?.viewmodel = viewModel
        return view
    }

    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }
}