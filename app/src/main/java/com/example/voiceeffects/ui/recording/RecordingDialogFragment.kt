package com.example.voiceeffects.ui.recording

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.voiceeffects.R
import com.example.voiceeffects.di.DaggerViewModelFactory
import com.example.voiceeffects.ui.base.BaseDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.recording_dialog_fragment.*
import javax.inject.Inject

class RecordingDialogFragment : BaseDialogFragment(), Animation.AnimationListener {
    private lateinit var animRotate: Animation

    private val viewModel: RecordingViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RecordingViewModel::class.java)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initView() {
        recording = true
        viewModel.trueRecordFlag()
        Thread(Runnable {
            viewModel.startRecordingVoice()
        }).start()

        animRotate = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_out)
        animRotate.setAnimationListener(this)
        imageView.visibility = View.INVISIBLE
        imageView.startAnimation(animRotate)
        stopRec.setOnClickListener {
            viewModel.falseRecordFlag()
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }
}