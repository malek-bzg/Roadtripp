package com.anypli.roadtriip.global.helper.dialog

import com.anypli.roadtriip.global.helper.TypeMessage

class SimpleDialog private constructor(
    val title: TypeMessage? = null ,
    val message: TypeMessage ,
    val okActionBlock: (() -> Unit)? = null ,
    val dismissActionBlock: (() -> Unit)? = null

) {

    companion object {
        fun build(
            title: TypeMessage? = null,
            message: TypeMessage,
            actionBlock: (() -> Unit)? = null,
            dismissActionBlock: (() -> Unit)? = null
        ): SimpleDialog {
            return SimpleDialog(title, message, actionBlock, dismissActionBlock);
        }
    }
}