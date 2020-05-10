package com.barkov.ais.mywellbeinghelper.tabsswipe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.barkov.ais.mywellbeinghelper.EmailHelper;
import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.SettingsWizardActivity;

import java.util.Random;

public class UserPersonalFragment extends ValidableFragment implements View.OnClickListener {

    Button btnAccountStep1;
    EditText txtName, txtSurname, txtEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        btnAccountStep1 = view.findViewById(R.id.btnAccountStep1);
        btnAccountStep1.setOnClickListener(this);

        txtName = view.findViewById(R.id.txtSearchParamClubName);
        txtSurname = view.findViewById(R.id.txtSurname);
        txtEmail = view.findViewById(R.id.txtEmail);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (!validatePersonalDetails()) {
            return;
        }

        EmailHelper emailHelper = new EmailHelper();
        int code = new Random().nextInt(8999) + 1000;
        emailHelper.send(
                txtEmail.getText().toString(),
                "My wellbeing helper email check",
                "Your verification code is " + code
        );
        ((SettingsWizardActivity)getActivity()).setVerificationCode(code);
        ((SettingsWizardActivity)getActivity()).nextTab();
    }

    protected boolean validatePersonalDetails()
    {
        boolean result = true;

        setValidInput(txtName);
        setValidInput(txtSurname);
        setValidInput(txtEmail);

        if (txtName.getText().length() <= 2  ||
                !txtName.getText().toString().matches("[a-zA-Z]+")) {
            setInvalidInput(txtName);
            result = false;
        }

        if (txtSurname.getText().length() <= 2 ||
                !txtSurname.getText().toString().matches("[a-zA-Z]+")) {
            setInvalidInput(txtSurname);
            result = false;
        }

        if (txtEmail.getText().length() <= 2 ||
                !txtEmail.getText().toString()
                        .matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            setInvalidInput(txtEmail);
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

        list.put("name", txtName.getText());
        list.put("surname", txtSurname.getText());
        list.put("email", txtEmail.getText());
        Log.d("dbg_userpersoanl", list.toString());
        return super.setProfileFields(list);
    }
}
