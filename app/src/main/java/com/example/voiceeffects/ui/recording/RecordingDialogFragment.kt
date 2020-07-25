package com.example.voiceeffects.ui.recording

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.voiceeffects.R
import com.example.voiceeffects.toast
import com.example.voiceeffects.ui.base.BaseDialogFragment
import com.example.voiceeffects.utils.Constants
import kotlinx.android.synthetic.main.recording_dialog_fragment.*

class RecordingDialogFragment : BaseDialogFragment(), Animation.AnimationListener {
    private lateinit var animRotate: Animation
    private var fileAccessGranted = false


    private val viewModel: RecordingViewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(RecordingViewModel::class.java)
        ViewModelProvider(this,viewModelFactory).get(RecordingViewModel::class.java)
    }

    private var recording: Boolean = false

    companion object {
        fun newInstance() = RecordingDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recording_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        requestReadAndWritePermissions()
        if (fileAccessGranted) {
            recording = true
            viewModel.trueRecordFlag()
            viewModel.startRecordingVoice()
            animRotate = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_out)
            animRotate.setAnimationListener(this)
            imageView.visibility = View.INVISIBLE
            imageView.startAnimation(animRotate)
            stopRec.setOnClickListener {
                viewModel.falseRecordFlag()
                dismiss()
            }
        }else{
            requireContext().toast("please let us access device storage")
            initView()
        }

    }

    override fun onAnimationRepeat(anim: Animation?) {
    }

    override fun onAnimationEnd(anim: Animation?) {
        imageView.startAnimation(this.animRotate)

    }

    override fun onAnimationStart(anim: Animation?) {
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    private fun requestReadAndWritePermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(Constants.readAndWritePermissions, Constants.STORAGE_PERMISSION_REQUEST_CODE)
        }else
        {
            fileAccessGranted = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fileAccessGranted = grantResults.takeIf { it.isNotEmpty() }
            ?.map { it == PackageManager.PERMISSION_GRANTED }
            ?.firstOrNull { it.not() }
            ?.let { false }
            ?: true
    }
}