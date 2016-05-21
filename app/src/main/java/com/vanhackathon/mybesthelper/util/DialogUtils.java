package com.vanhackathon.mybesthelper.util;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.vanhackathon.mybesthelper.R;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class DialogUtils {
    public static Dialog showTryAgain(Context context, String title, String message, DialogInterface.OnClickListener positiveButtonListener) {
        return getBuilder(context, title, message, positiveButtonListener, R.string.general_ok, R.string.general_cancel).show();
    }

    public static AlertDialog.Builder getBuilder(Context context, String title, String message,
                                                 DialogInterface.OnClickListener positiveButtonListener,
                                                 @StringRes int... buttonStringResIds) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);
        if (buttonStringResIds == null || buttonStringResIds.length <= 0) {
            builder.setPositiveButton(R.string.general_ok, positiveButtonListener);
        } else {
            builder.setPositiveButton(buttonStringResIds[0], positiveButtonListener);
            if (buttonStringResIds.length > 1) {
                builder.setNegativeButton(buttonStringResIds[1], null);
            }
        }
        return builder;
    }
}
