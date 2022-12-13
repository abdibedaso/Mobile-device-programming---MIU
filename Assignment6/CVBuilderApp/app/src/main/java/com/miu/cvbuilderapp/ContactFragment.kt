package com.miu.cvbuilderapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.cvbuilderapp.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_contact, container, false)
        binding = FragmentContactBinding.bind(view)
        //
        binding.mobile.img.setImageResource(R.drawable.call)
        binding.mobile.tv1.text = resources.getString(R.string.ctPhone)
        binding.mobile.tv2.text = resources.getString(R.string.ctPhoneLabel)
        binding.mobile.tv1.setOnClickListener{
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            val et2 = resources.getString(R.string.ctPhone)
            i.data = Uri.parse("tel:$et2")
            startActivity(i)
        }

        binding.email.img.setImageResource(R.drawable.email)
        binding.email.tv1.text = resources.getString(R.string.ctEmail)
        binding.email.tv2.text = resources.getString(R.string.ctEmailLabel)
        binding.email.tv1.setOnClickListener{
            //send email implicit intent
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", resources.getString(R.string.ctEmail), null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Via CV Builder App")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I'd like to connect you.")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }

        binding.linkedin.img.setImageResource(R.drawable.linkedin_gray)
        binding.linkedin.tv1.text = resources.getString(R.string.ctLinkedIn)
        binding.linkedin.tv2.text = resources.getString(R.string.ctLinkedInLabel)

        binding.github.img.setImageResource(R.drawable.github)
        binding.github.tv1.text = resources.getString(R.string.ctGit)
        binding.github.tv2.text = resources.getString(R.string.ctGitLabel)

        binding.resume.img.setImageResource(R.drawable.pdf)
        binding.resume.tv1.text = resources.getString(R.string.ctResume)
        binding.resume.tv2.text = resources.getString(R.string.ctResumeLabel)

        binding.resume.tv2.setOnClickListener{
            val uri = Uri.parse("https://1drv.ms/b/s!AsIJBY2JHCGd7fVWH23drsGmxm8dlg?e=ZG9KF6");
            val it = Intent(Intent.ACTION_VIEW,uri);
            startActivity(it)
        }
        return view
    }
}