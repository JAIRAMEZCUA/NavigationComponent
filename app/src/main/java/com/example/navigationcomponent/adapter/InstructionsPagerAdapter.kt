package com.example.navigationcomponent.adapter

import android.content.res.TypedArray
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.navigationcomponent.R
import com.example.navigationcomponent.intructions.InstructionsSectionFragment

class InstructionsPagerAdapter(parent: Fragment, list: Array<String>, drawables: TypedArray?) :
    FragmentStateAdapter(parent) {

    private val sections = ArrayList<Fragment>()

    init {
        for (num in list.indices) {
            //getResourceId index and default value
            val res = drawables?.getResourceId(num, R.drawable.ic_launcher_foreground)
            sections.add(
                InstructionsSectionFragment.newInstance(
                    list[num],
                    res!!
                )
            )
        }
    }

    //TODO PARA ACCESIBILIDAD
    /*   fun requestFocus(position: Int) {
           (sections[position] as InstructionsSectionFragment).requestFocus()
       }*/

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun createFragment(position: Int): Fragment {
        return sections[position]
    }
}