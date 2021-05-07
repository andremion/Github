package com.andremion.github.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andremion.github.R
import com.andremion.github.di.ViewModelFactory
import com.andremion.github.ui.main.di.MainInjector

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var screen: MainScreen
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainInjector.inject(this)
        viewModel.state.observe(viewLifecycleOwner, screen::render)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()
    }
}
