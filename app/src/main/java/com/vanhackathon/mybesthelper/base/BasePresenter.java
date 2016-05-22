package com.vanhackathon.mybesthelper.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class BasePresenter {
    public Context context;

    public BasePresenter(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int stringRes) {
        return context.getString(stringRes);
    }

    public String getString(@StringRes int stringRes, Object... parameters) {
        return context.getString(stringRes, parameters);
    }
}
