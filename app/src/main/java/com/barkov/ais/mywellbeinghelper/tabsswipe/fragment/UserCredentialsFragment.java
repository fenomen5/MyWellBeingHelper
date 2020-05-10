package com.barkov.ais.mywellbeinghelper.tabsswipe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.barkov.ais.mywellbeinghelper.EmailHelper;
import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.SettingsWizardActivity;

import java.util.ArrayList;
import java.util.Random;

public class UserCredentialsFragment extends ValidableFragment implements View.OnClickListener {

    Button btnRegister;
    EditText txtLogin, txtPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_credentials, container, false);

        btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        txtLogin = view.findViewById(R.id.txtLogin);
        txtPassword = view.findViewById(R.id.txtPassword);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (!validatePersonalDetails()) {
            return;
        }
        ((SettingsWizardActivity)getActivity()).registerUser();
    }

    protected boolean validatePersonalDetails()
    {
        boolean result = true;

        setValidInput(txtLogin);
        setValidInput(txtPassword);

        if (txtLogin.getText().length() <= 2  ||
                !txtLogin.getText().toString().matches("[a-zA-Z]+")) {
            setInvalidInput(txtLogin);
            result = false;
        }

        if (txtPassword.getText().length() <= 4) {
            setInvalidInput(txtPassword);
            result = false;
        }

        return result;
    }

    protected void setInvalidInput(View v)
    {
        v.setBackgroundColor(getResources().getColor(R.color.colorErrorRed));
    }

    protected void setValidInput(View v) {
        v.setBackgroundColor(getResources().getColor(R.color.colorBackgroundGreen));
    }

    @Override
    public boolean validateInput()
    {
        return validatePersonalDetails();
    }

    @Override
    public ArrayMap setProfileFields(ArrayMap list) {

        list.put("login", txtLogin.getText());
        list.put("password", txtPassword.getText());
        return super.setProfileFields(list);
    }
}
