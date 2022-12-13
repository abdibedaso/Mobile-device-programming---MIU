package com.miu.cvbuilderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var rv : RecyclerView //this initialization is required for fragment and adapter binding
    //adapter မှာ change စရာမလို
    var imageges = intArrayOf(
        R.drawable.note,
        R.drawable.work
    )
    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        //card view bind
        var s1 = resources.getStringArray(R.array.homeTitle)
        var s2 = resources.getStringArray(R.array.homeDesc)
        rv = view.findViewById(R.id.rv)

        if(context != null) {
            rv.layoutManager = LinearLayoutManager(context)
            val adapter = context?.let { HomeAdapter(it, s1, s2, imageges) }
            rv.adapter = adapter
            // Inflate the layout for this fragment
        }
        return view
    }
}