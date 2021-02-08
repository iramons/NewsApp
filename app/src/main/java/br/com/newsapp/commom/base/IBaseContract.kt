package br.com.newsapp.commom.base

internal interface IBaseContract {
    val layoutId: Int
    fun initViewModel()
    fun initUI()
    fun initObservers()
}