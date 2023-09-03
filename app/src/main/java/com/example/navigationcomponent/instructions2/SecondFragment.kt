package com.example.navigationcomponent.instructions2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.navigationcomponent.R
import com.example.navigationcomponent.adapter.InstructionsPagerAdapter
import com.example.navigationcomponent.databinding.FragmentFirstBinding
import com.example.navigationcomponent.databinding.FragmentSecondBinding
import com.example.navigationcomponent.intructions.InstructionsSectionFragment
import com.google.android.material.tabs.TabLayoutMediator

class SecondFragment : Fragment() {

    val args: SecondFragmentArgs by navArgs()
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "EL VALOR ES ${args.name}")
        val listText = activity?.resources?.getStringArray(R.array.instructions_text)
        val adapter = RecyclerPagerAdapter(listText!!)
        binding.vpInstructions.adapter = adapter
        binding.vpInstructions.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                binding.button.isEnabled =
                    position == (binding.vpInstructions.adapter as RecyclerPagerAdapter).itemCount - 1
            }
        })

        //attach to TabLayout
        TabLayoutMediator(
            binding.tblDots,
            binding.vpInstructions
        ) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
    }
}