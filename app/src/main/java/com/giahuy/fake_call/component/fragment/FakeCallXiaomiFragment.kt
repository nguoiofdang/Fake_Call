package com.giahuy.fake_call.component.fragment

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.giahuy.fake_call.R
import com.giahuy.fake_call.databinding.FragmentFakeCallPixelBinding

class FakeCallXiaomiFragment : BaseFragment<FragmentFakeCallPixelBinding>() {

    private lateinit var animation: Animation
    private lateinit var mediaPlayer: MediaPlayer

    override fun initView() {
        animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_sb_call).also {
            binding.sbCall.startAnimation(it)
        }
    }

    override fun initData() {
        val uriRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        mediaPlayer = MediaPlayer.create(requireContext(), uriRingtone)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().finish()
        }, 10000)
    }

    override fun initListener() {
        binding.sbCall.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress >= 80) {
                    binding.sbCall.thumb = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.thumb_call_pixel_start
                    )
                    binding.sbCall.progress = 80
                } else if (progress <= 20) {
                    binding.sbCall.thumb = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.thumb_call_pixel_end
                    )
                    binding.sbCall.progress = 20
                } else {
                    binding.sbCall.thumb = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.thumb_call_pixel_default
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("check", "start")
                binding.sbCall.clearAnimation()
                binding.apply {
                    tvInstructionUp.visibility = View.INVISIBLE
                    tvInstructionDown.visibility = View.INVISIBLE
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("check", "end")
                binding.sbCall.startAnimation(animation)
                binding.apply {
                    tvInstructionUp.visibility = View.VISIBLE
                    tvInstructionDown.visibility = View.VISIBLE
                }
                binding.sbCall.progress = 40
                binding.sbCall.thumb = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.thumb_call_pixel_default
                )
            }

        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFakeCallPixelBinding {
        return FragmentFakeCallPixelBinding.inflate(inflater, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}