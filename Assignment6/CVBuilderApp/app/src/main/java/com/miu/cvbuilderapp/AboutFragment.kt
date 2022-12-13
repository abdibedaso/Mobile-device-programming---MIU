package com.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import com.miu.cvbuilderapp.databinding.FragmentAboutBinding
import kotlinx.android.synthetic.main.home_card_layout.*

class AboutFragment : Fragment() {
    private lateinit var binding:FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_about, container, false)
        //About Me
        binding = FragmentAboutBinding.bind(view)
        binding.about.tv1.text = resources.getString(R.string.abtTitle)
        binding.about.tv2.text = resources.getString(R.string.abtDesc)
        binding.about.img.setImageResource(R.drawable.person)

        //Education
        binding.edu.divTitle.text = resources.getString(R.string.eduTitle)
        binding.edu.iconImg.setImageResource(R.drawable.education)

        binding.edu.item1.img.setImageResource(R.drawable.miu)
        binding.edu.item1.tv1.text = resources.getString(R.string.eduMaster2Title)
        binding.edu.item1.tv2.text = resources.getString(R.string.eduMaster2Desc)

        binding.edu.item3.img.setImageResource(R.drawable.ddu)
        binding.edu.item3.tv1.text = resources.getString(R.string.eduBachelorTitle)
        binding.edu.item3.tv2.text = resources.getString(R.string.eduBachelorDesc)

        //Certification
        binding.cert.divTitle.text = resources.getString(R.string.certTitle)
        binding.cert.iconImg.setImageResource(R.drawable.cert)

        binding.cert.item1.img.setImageResource(R.drawable.mcts1)
        binding.cert.item1.tv1.text = resources.getString(R.string.cert1)
        binding.cert.item1.tv2.isInvisible = true

        binding.cert.item3.root.isGone = true
        return view
    }
}