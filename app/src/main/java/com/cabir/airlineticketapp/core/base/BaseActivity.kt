package com.cabir.airlineticketapp.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

abstract class BaseActivity<VM: BaseViewModel, DB: ViewDataBinding>: AppCompatActivity() {

    protected lateinit var binding: DB
    protected abstract val viewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected open fun initUI(){}
    protected open fun initListener(){}
    protected open fun onChangedScreenState(state:VMState){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        viewModel.state.observe(this) { onChangedScreenState(it) }
        initUI()
        initListener()
    }
}