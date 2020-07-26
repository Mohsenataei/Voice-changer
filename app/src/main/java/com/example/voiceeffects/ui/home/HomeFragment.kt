package com.example.voiceeffects.ui.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.voiceeffects.R
import com.example.voiceeffects.event.RequestPermissionEvent
import com.example.voiceeffects.extensions.checkSelfPermissions
import com.example.voiceeffects.extensions.toast
import com.example.voiceeffects.ui.base.BaseFragment
import com.example.voiceeffects.ui.recording.RecordingDialogFragment
import com.example.voiceeffects.utils.Constants.STORAGE_PERMISSION_REQUEST_CODE
import com.example.voiceeffects.utils.Constants.READ_AND_WRITE_PERMISSIONS
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private lateinit var adapter: ArrayAdapter<String>

    private var fileAccessPermission = false

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
    }


    private fun initView() {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1)
        viewModel.filters.observe(viewLifecycleOwner, Observer {
            initAdapter(it)
        })
        viewModel.isPermissionGranted.observe(viewLifecycleOwner, Observer { event ->
            event.validContent?.let {
                handlePermissions(it)
            }
        })
        startRecord.setOnClickListener {
            if (requireContext().checkSelfPermissions(READ_AND_WRITE_PERMISSIONS))
                openRecordingDialog()
            else {
                checkPermissions()
            }
        }

        playRecord.setOnClickListener {
            viewModel.playRecord(voiceEffects.selectedItem.toString())
//            viewModel.play()
        }
    }

    private fun handlePermissions(it: RequestPermissionEvent) {

    }

    private fun initAdapter(items: List<String>) {
        adapter.addAll(items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        voiceEffects.adapter = adapter
    }

    private fun checkPermissions() {
        if (!fileAccessPermission) {
            if (requireContext().checkSelfPermissions(READ_AND_WRITE_PERMISSIONS)) {
                fileAccessPermission = true
            } else {
                requestPermissions(READ_AND_WRITE_PERMISSIONS, STORAGE_PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_REQUEST_CODE -> {
//                fileAccessPermission = grantResults.takeIf { it.isNotEmpty() }
//                    ?.map { it == PackageManager.PERMISSION_GRANTED }
//                    ?.firstOrNull { it.not() }
//                    ?.let { true }
//                    ?: false
                checkGrantPermissions(grantResults)

                if (fileAccessPermission) openRecordingDialog()


            }
        }
    }

    private fun openRecordingDialog() {
        RecordingDialogFragment.newInstance()
            .show(childFragmentManager, "Recording dialog fragment")
    }

    private fun checkGrantPermissions(grantResults: IntArray) {
        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) {
                fileAccessPermission = false
                return
            }
        }
        fileAccessPermission = true
    }
}