package com.miu.cvbuilderapp

import java.io.Serializable

data class Work(
    val position: String,
    val company: String,
    val duration: String,
    val location: String,
    val desc:String,
    val image: Int
) : Serializable