package com.fok.mob21firebase.ui.addEditTodo.viewModel

import com.fok.mob21firebase.data.repo.TodosRepo
import com.fok.mob21firebase.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseAddEditTodoViewModel: BaseViewModel() {
    protected val _finish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val finish: SharedFlow<Unit> = _finish

    abstract fun submit(title:String, desc: String)
}