package com.fok.mob21firebase.ui.addEditTodo

import androidx.fragment.app.viewModels
import com.fok.mob21firebase.ui.addEditTodo.viewModel.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment: BaseAddEditTodoFragment() {
    override val viewModel: AddTodoViewModel by viewModels()
}