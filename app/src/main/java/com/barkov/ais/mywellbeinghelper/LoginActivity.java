package com.barkov.ais.mywellbeinghelper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.entity.Login;
import com.barkov.ais.mywellbeinghelper.entity.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {

    EditText txtLogin, txtPassword;
    Button btnLogin;
    TextView lblLoginForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);

        lblLoginForm = findViewById(R.id.lblLoginForm);
    }

    @Override
    public void onClick(View v)
    {
        boolean result = processLogin(txtLogin.getText().toString(), txtPassword.getText().toString());
        if (result) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        displayInvalidLogin();
    }

    protected boolean processLogin(String login, String password)
    {
        DataBaseHelper dbHelper;
        dbHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        
        RuntimeExceptionDao loginDao = dbHelper.getLoginRuntimeDao();
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("login", login);
        values.put("password", password);
        List<Login> logins = loginDao.queryForFieldValues(values);

        if (logins.size() > 0) {
            return true;
        }

        return  false;
    }

    protected void displayInvalidLogin()
    {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.wrong_login);
        lblLoginForm.setText("login or password are incorrect");
        lblLoginForm.setTextColor(getResources().getColor(R.color.colorErrorRed));
        lblLoginForm.startAnimation(animShake);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lblLoginForm.setText("Login");
                lblLoginForm.setTextColor(getResources().getColor(R.color.title_white));
            }
        },3000);

    }
}
