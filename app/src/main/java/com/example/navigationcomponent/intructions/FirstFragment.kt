package com.example.navigationcomponent.intructions

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.navigationcomponent.R
import com.example.navigationcomponent.intructions.adapter.InstructionsPagerAdapter
import com.example.navigationcomponent.databinding.FragmentFirstBinding
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnInstructionsContinue.isEnabled = false
        binding.btnInstructionsContinue.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
//            TODO SAFE FRAGMENT CREAN ESTAS CLASES POR DETRAS
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment2(
                    name = "JAIR"
                )
            )
        }

        //List of text and images
        val listDrawables: TypedArray? =
            activity?.resources?.obtainTypedArray(R.array.instructions_drawables)
        val listText = activity?.resources?.getStringArray(R.array.instructions_text)

        //set adapter
        binding.vpInstructions.adapter =
            InstructionsPagerAdapter(this, listText as Array<String>, listDrawables)

        binding.vpInstructions.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                if (binding.vpInstructions.currentItem == 0) {
                    return
                } else {
                    binding.btnInstructionsContinue.isEnabled =
                        position == (binding.vpInstructions.adapter as InstructionsPagerAdapter).itemCount - 1
                    // TODO Esta linea es para la accesiillidad que se haga el requestfocus (binding.vpInstructions.adapter as InstructionsPagerAdapter).requestFocus(position)
                }

            }
        })

        //attach to TabLayout
        TabLayoutMediator(
            binding.tblDots,
            binding.vpInstructions
        ) { _, _ -> }.attach()
    }
}