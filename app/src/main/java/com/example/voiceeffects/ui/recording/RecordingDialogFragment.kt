package com.example.voiceeffects.ui.recording

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.example.voiceeffects.R
import com.example.voiceeffects.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.recording_dialog_fragment.*

private const val TAG = "RecordingDialogFragment"
@SuppressLint("LogNotTimber")
class RecordingDialogFragment : BaseDialogFragment(), Animation.AnimationListener {
    private lateinit var animRotate: Animation
    private var fileAccessGranted = false




    private val viewModel: RecordingViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RecordingViewModel::class.java)
    }

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
        viewModel.StartRecorder()
        animRotate = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_out)
        animRotate.setAnimationListener(this)
        imageView.visibility = View.INVISIBLE
        imageView.startAnimation(animRotate)
        stopRec.setOnClickListener {
            viewModel.stopRecording()
            dismiss()
        }
    }

    override fun onAnimationRepeat(anim: Animation?) {
    }

    override fun onAnimationEnd(anim: Animation?) {
        imageView.startAnimation(this.animRotate)

    }

    override fun onAnimationStart(anim: Animation?) {
    }

}

