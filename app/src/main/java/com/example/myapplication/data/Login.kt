package com.example.myapplication.data

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.application.ApplicationTest

class Login(

) : BaseObservable() {
    @get:Bindable
    var id: String = "asdf"
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }


    @get:Bindable
    var passWd: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.passWd)
        }

    @Bindable
    fun getWelcomeStr(): String {
        return if (!TextUtils.isEmpty(id)) {
            ApplicationTest.instance.resources.getString(
                R.string.success_login,
                id
            )
        } else {
            ApplicationTest.getResource().getString(R.string.need_login)
        }
    }
//    lateinit var id: String;
//    lateinit var passWd: String;
    //    var id: MutableLiveData<String> = MutableLiveData("")
//    var passwd: MutableLiveData<String> = MutableLiveData("")
//    var memberId: MutableLiveData<String> = MutableLiveData("")

}
