package com.example.kitpo_rgr

import androidx.lifecycle.ViewModel
import com.example.kitpo_rgr.Builder.UserType
import com.example.kitpo_rgr.List.TList

class MainActivityViewModel : ViewModel() {
    var builder: UserType = UserFactory.getBuilderByName("Integer")
    var list: TList = TList(builder)
    var logText: String = ""
}