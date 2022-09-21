package com.cabir.airlineticketapp.ui.main

import androidx.activity.viewModels
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.core.base.BaseActivity
import com.cabir.airlineticketapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutRes: Int = R.layout.activity_main
}