package com.anypli.roadtriip.base

import androidx.lifecycle.ViewModel
import com.anypli.roadtriip.R
import com.anypli.roadtriip.global.helper.BaseStateContent
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.global.helper.TypeMessage
import com.anypli.roadtriip.global.helper.dialog.ChooseDialog
import com.anypli.roadtriip.global.helper.dialog.SimpleDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    //for blocking progress bar
    private val _progressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBar: StateFlow<Boolean>
        get() = _progressBar

    //for displaying simple dialog
    private val _simpleDialog: MutableStateFlow<SimpleDialog?> = MutableStateFlow(null)
    val simpleDialog: StateFlow<SimpleDialog?>
        get() = _simpleDialog

    //for displaying chose dialog
    private val _chooseDialog: MutableStateFlow<ChooseDialog?> = MutableStateFlow(null)
    val chooseDialog: StateFlow<ChooseDialog?>
        get() = _chooseDialog

    //for displaying global snack bar
    private val _snackBarMessage: MutableStateFlow<BaseStateContent<TypeMessage>?> = MutableStateFlow(null)
    val snackBarMessage: StateFlow<BaseStateContent<TypeMessage>?>
        get() = _snackBarMessage

    private val _navigation = MutableStateFlow<BaseStateContent<Navigation>?>(null)
    val navigation: StateFlow<BaseStateContent<Navigation>?>
        get() = _navigation

    fun onSnackBarDisplayed(id: Long) {
        if (_snackBarMessage.value?.id == id) {
            _snackBarMessage.value = null
        }
    }
    protected fun navigate(navigationTo: Navigation) {
        _navigation.value = BaseStateContent(content = navigationTo)
    }
    protected fun showSnackBarMessage(message: TypeMessage) {
        _snackBarMessage.value = BaseStateContent(content = message)
    }
    private fun setShowBlockProgress(show: Boolean) {
        _progressBar.value = show
    }
    protected fun showBlockProgressBar() {
        setShowBlockProgress(true)
    }
    protected fun hideBlockProgressBar() {
        setShowBlockProgress(false)
    }
    fun showSimpleDialog(
        title: TypeMessage? = null,
        message: TypeMessage,
        okActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {
        _simpleDialog.value = SimpleDialog.build(
            title,
            message,
            dismissSimpleBuild(okActionBlock),
            dismissSimpleBuild(dismissActionBlock)
        )
    }
    private fun dismissSimpleBuild(dismissActionBlock: (() -> Unit)? = null): () -> Unit {
        return {
            dismissActionBlock?.invoke()
            _simpleDialog.value = null
        }
    }
    fun showChoseDialog(
        title: TypeMessage? = null,
        message: TypeMessage,
        ok: TypeMessage,
        cancel: TypeMessage,
        okActionBlock: (() -> Unit)? = null,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {
        _chooseDialog.value =
            ChooseDialog.build(
                title ,
                message ,
                ok ,
                cancel ,
                dismissChoseBuild(okActionBlock) ,
                dismissChoseBuild(cancelActionBlock)  ,
                dismissChoseBuild(dismissActionBlock) ,
            )
    }
    private fun dismissChoseBuild(dismissActionBlock: (() -> Unit)? = null): () -> Unit {
        return {
            dismissActionBlock?.invoke()
            _chooseDialog.value = null
        }
    }
    fun onNavigationConsumed() {
        _navigation.value = null
    }
    fun handleGlobalError(exception: Exception){
        if (exception is IOException){
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_network_error))
        }else{
            showSimpleDialog(message = TypeMessage.ResourceMessage(R.string.global_server_error))

        }
    }









}