package com.kirichko.testtask.Listeners;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kirichko.testtask.Activities.MainActivity;
import com.kirichko.testtask.R;

/**
 * Created by Киричко on 06.09.2015.
 */
public class OnEnterButtonClickListener implements View.OnClickListener {

    private EditText mLogin;
    private EditText mPassword;
    private Context mContext;
    private Drawable mRedFrame;

    public OnEnterButtonClickListener(Context context, EditText login, EditText password)
    {
        mLogin = login;
        mPassword = password;
        mContext = context;
        mRedFrame = getDrawable(mContext, R.drawable.shape_for_red_border_edit_text);
    }

    @Override
    public void onClick(View v) {

        if(mLogin.getText().length() == 0) { setBackground(mRedFrame, mLogin); }
        if(mPassword.getText().length() == 0 ) { setBackground(mRedFrame, mPassword);}
        if(mPassword.getText().length() != 0 && mLogin.getText().length() != 0)
        {
            ((MainActivity)mContext).prepareTariffsFragment(mLogin.getText().toString(), mPassword.getText().toString());
        }
        View view = ((Activity)mContext).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Drawable getDrawable(Context context, int id)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    private void setBackground(Drawable drawable, EditText editText)
    {
        if (android.os.Build.VERSION.SDK_INT >= 16){
            editText.setBackground(drawable);
        }
        else{
            editText.setBackgroundDrawable(drawable);
        }
    }
}
