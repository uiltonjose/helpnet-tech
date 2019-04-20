package com.helpnet.tech.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
import com.helpnet.tech.R

object AlertDialogUtil {

    private val okClickListener = DialogInterface.OnClickListener { dialogInterface, _ -> dialogInterface.dismiss() }

    fun showMessageOK(
        context: Context?,
        title: String? = null,
        message: String,
        okLabel: Int = -1,
        okListener: DialogInterface.OnClickListener? = okClickListener
    ) {
        val okButtonStr = if (okLabel == -1) R.string.ok else okLabel

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setMessage(message)
        builder.setPositiveButton(okButtonStr, okListener)
        if (title != null) {
            builder.setTitle(title)
        }

        builder.create()?.apply {
            setCanceledOnTouchOutside(false)
            window?.setType(TYPE_APPLICATION_PANEL)
            show()
        }
    }

    fun showMessageOKCancel(
        context: Context?,
        title: String? = null,
        message: String,
        okLabel: Int = -1,
        cancelLabel: Int = -1,
        okListener: DialogInterface.OnClickListener? = okClickListener,
        onCancelListener: DialogInterface.OnClickListener? = null
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)

        val finalOkLabel = if (okLabel == -1) R.string.yes else okLabel
        builder.setPositiveButton(finalOkLabel, okListener)

        val finalCancelLabel = if (cancelLabel == -1) R.string.no else cancelLabel
        builder.setNeutralButton(finalCancelLabel, onCancelListener)

        builder.setCancelable(false)

        title?.also {
            builder.setTitle(it)
        }

        val dialog = builder.create()
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setType(TYPE_APPLICATION_PANEL)
            dialog.show()
        }
    }

    fun showCustomDialog(
        context: Context?,
        title: String?,
        view: View,
        okLabel: Int = -1,
        cancelLabel: Int = -1,
        okListener: DialogInterface.OnClickListener? = okClickListener,
        onCancelListener: DialogInterface.OnClickListener? = null
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        title?.also {
            builder.setTitle(it)
        }

        val finalOkLabel = if (okLabel == -1) R.string.yes else okLabel
        builder.setPositiveButton(finalOkLabel, okListener)

        val finalCancelLabel = if (cancelLabel == -1) R.string.no else cancelLabel
        builder.setNeutralButton(finalCancelLabel, onCancelListener)

        builder.setCancelable(false)

        val dialog = builder.create()
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setType(TYPE_APPLICATION_PANEL)
            dialog.show()
        }
    }
}
