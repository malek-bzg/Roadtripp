package com.anypli.roadtriip.global.helper

import java.util.*

data class BaseStateContent<T>(
    val id: Long = UUID.randomUUID().mostSignificantBits ,
    val content: T
)