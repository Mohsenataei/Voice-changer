package com.example.voiceeffects.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.voiceeffects.R
import com.example.voiceeffects.ui.base.BaseFragment
import com.example.voiceeffects.ui.recording.RecordingDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)
    }
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
//        RecordingFragmentDirections
    }


    private fun initView() {

        viewModel.filters.observe(viewLifecycleOwner, Observer {
            initAdapter(it)
        })
        startRecord.setOnClickListener {
            RecordingDialogFragment.newInstance().show(childFragmentManager,"")
        }

        playRecord.setOnClickListener {
            viewModel.playRecord(voiceEffects.selectedItem.toString())
        }
    }

    private fun initAdapter(items: List<String>) {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        voiceEffects.adapter = adapter
    }

}