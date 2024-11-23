package com.amir.aram.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(title: String){
    Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
}