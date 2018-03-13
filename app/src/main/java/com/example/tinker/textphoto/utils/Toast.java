/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tinker.textphoto.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Toast工具类
 */
public class Toast {

    private static android.widget.Toast mToast;

    public static android.widget.Toast show(Context context, CharSequence msg) {
        if (msg == null || msg.length() == 0) {
            return null;
        }
        if (mToast == null) {
            mToast = android.widget.Toast.makeText(context,
                    msg,
                    android.widget.Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg.toString());
        }
        mToast.setDuration(android.widget.Toast.LENGTH_SHORT);
        mToast.show();
        return mToast;
    }

    public static android.widget.Toast show(Context context, @StringRes int stringId) {
        if (mToast == null) {
            mToast = android.widget.Toast.makeText(context,
                    context.getString(stringId),
                    android.widget.Toast.LENGTH_SHORT);
        } else {
            mToast.setText(context.getString(stringId));
        }
        mToast.setDuration(android.widget.Toast.LENGTH_SHORT);
        mToast.show();
        return mToast;
    }

    public static void show(View view, CharSequence msg) {
        show(view.getContext(), msg);
    }

    public static void show(View view, @StringRes int stringId) {
        show(view.getContext(), stringId);
    }

}
