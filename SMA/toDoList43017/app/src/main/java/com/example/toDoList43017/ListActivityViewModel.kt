package com.example.toDoList43017

import android.app.Activity
import android.content.Context
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.widget.PopupMenu


class ListActivityViewModel: ViewModel() {

    fun resetScreen(activity: Activity, itemView: View, textView: TextView){

        // Faz a caixa de texto desaparecer
        textView.text = ""
        textView.visibility = View.INVISIBLE

        // Faz o teclado desaparecer
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(itemView.windowToken, 0)

    }

    fun isEmpty(textView: TextView): Boolean{

        return textView.text.toString()==""

    }

    fun showMenu(applicationContext: Context, itemView: View): PopupMenu{

        val popup = PopupMenu(applicationContext, itemView)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu, popup.menu)
        popup.show()

        return popup
    }
}