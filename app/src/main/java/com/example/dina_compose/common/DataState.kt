package com.example.dina_compose.common

sealed class DataState<out R>{
    object Loading:DataState<Nothing>()
    data class Result<out Result>(val data:Result):DataState<Result>()
    data class Error(val message:String=""):DataState<Nothing>()
}