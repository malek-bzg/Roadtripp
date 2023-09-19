package com.anypli.roadtriip.global.helper

import androidx.annotation.StringRes

sealed class TypeMessage {
    data class StringMessage( val message: String): TypeMessage()
    data class ResourceMessage(@StringRes  val messageId: Int): TypeMessage()
}