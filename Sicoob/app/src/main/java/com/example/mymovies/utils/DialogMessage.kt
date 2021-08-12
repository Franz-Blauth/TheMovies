package com.example.mymovies.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.mymovies.R

class DialogMessage {

    fun showMessage(message: String, context: Context) {

        val builder = AlertDialog.Builder(context)

        with(builder) {
            setTitle(R.string.MyMovies)
            setMessage(message)
            setIcon(R.drawable.ic_dialog_message)
            setPositiveButton(R.string.ok) { _, _ ->
                Toast.makeText(context, R.string.null_msg, Toast.LENGTH_SHORT)
            }
                .show()

        }
    }
}