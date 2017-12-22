package com.lwx.mvprx.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lwx.mvprx.R;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyDailog extends AlertDialog {

    public MyDailog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        setCanceledOnTouchOutside(false);
    }
}
