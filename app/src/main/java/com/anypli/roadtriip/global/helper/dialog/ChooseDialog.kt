package com.anypli.roadtriip.global.helper.dialog

import com.anypli.roadtriip.global.helper.TypeMessage

class ChooseDialog private constructor(
    val title: TypeMessage? = null,
    val message: TypeMessage,
    val ok: TypeMessage,
    val cancel: TypeMessage,
    val okActionBlock: (() -> Unit)? = null,
    val cancelActionBlock: (() -> Unit)? = null,
    val dismissActionBlock: (() -> Unit)? = null
) {

    companion object {
        fun build(
            title: TypeMessage? = null , message: TypeMessage ,
            ok: TypeMessage , cancel: TypeMessage , okActionBlock: (() -> Unit)? = null , cancelActionBlock: (() -> Unit)? = null ,
            dismissActionBlock: (() -> Unit)? = null
        ): ChooseDialog {
            return ChooseDialog(title, message, ok, cancel, okActionBlock, cancelActionBlock, dismissActionBlock);
        }
    }
}