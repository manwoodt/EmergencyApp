package com.course.ex1

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.course.ex1.databinding.FragmentZonesBinding
import com.course.ex1.viewmodel.ZonesViewModel

class ZonesFragment : Fragment() {

    private var _binding: FragmentZonesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZonesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentZonesBinding.inflate(inflater, container, false)
        val adapter
       return binding.root
    }
}