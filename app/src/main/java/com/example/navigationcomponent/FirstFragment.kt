package com.example.navigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_first, container, false)
        root.findViewById<Button>(R.id.button).setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
//            TODO SAFE FRAGMENT CREAN ESTAS CLASES POR DETRAS
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment2(
                    name = "JAIR"
                )
            )
        }
        return root
    }
}