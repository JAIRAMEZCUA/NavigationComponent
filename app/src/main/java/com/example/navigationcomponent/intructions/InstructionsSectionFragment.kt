package com.example.navigationcomponent.intructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentInstructionsSectionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InstructionsSectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_TEXT_ID = "textId"
private const val ARG_IMAGE_ID = "imageId"

class InstructionsSectionFragment : Fragment() {

    private var textId: String = ""

    @DrawableRes
    private var imageId: Int? = null

    private lateinit var binding: FragmentInstructionsSectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textId = it.getString(ARG_TEXT_ID).toString()
            imageId = it.getInt(ARG_IMAGE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsSectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.tvInstructionsText.text = textId
            binding.imgInstructionsImage.setImageResource(imageId!!)
        } catch (e: Exception) {
            binding.tvInstructionsText.text = ""
            binding.imgInstructionsImage.setImageResource(R.drawable.ic_instructions_document_1)
            Toast.makeText(context, "Invalid resources", Toast.LENGTH_SHORT).show()
        }
    }

    //TODO to accesibility
    /*   fun requestFocus() {
           binding.tvInstructionsText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
       }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param textId Parameter 1.
         * @param imageId Parameter 2.
         * @return A new instance of fragment InstructionsSectionFragment.
         */
        @JvmStatic
        fun newInstance(textId: String, @DrawableRes imageId: Int) =
            InstructionsSectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXT_ID, textId)
                    putInt(ARG_IMAGE_ID, imageId)
                }
            }
    }
}