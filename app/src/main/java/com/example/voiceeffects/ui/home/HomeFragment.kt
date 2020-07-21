package com.example.voiceeffects.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.voiceeffects.NavGraphDirections
import com.example.voiceeffects.R
import com.example.voiceeffects.ui.recording.RecordingDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()
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

        viewModel.arrayString.observe(viewLifecycleOwner, Observer {
            initAdapter(it)
        })
        buttonStart.setOnClickListener {
            val dialog = RecordingDialogFragment()
            dialog.show(childFragmentManager, "")

        }
        stopRecording.setOnClickListener {
            viewModel.stopRecording()
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