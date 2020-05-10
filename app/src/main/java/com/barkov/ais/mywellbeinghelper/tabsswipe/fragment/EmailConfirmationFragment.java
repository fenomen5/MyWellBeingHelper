package com.barkov.ais.mywellbeinghelper.tabsswipe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.SettingsWizardActivity;

import java.util.ArrayList;


public class EmailConfirmationFragment extends ValidableFragment
        implements View.OnClickListener {

    Button btnCheckEmail;
    EditText txtCode;
    TextView lblMessage;
    boolean codeValid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_check, container, false);

        btnCheckEmail = view.findViewById(R.id.btnRegister);
        btnCheckEmail.setOnClickListener(this);

        txtCode = view.findViewById(R.id.txtCode);
        lblMessage = view.findViewById(R.id.lblMessage);

        codeValid = false;
        return view;
    }

    @Override
    public void onClick(View v)
    {
        if (txtCode.getText().length() == 0) {
            return;
        }

        int code = -1;
        try {
            code = Integer.parseInt(txtCode.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            setCodeInvalid();
            return;
        }

        if (this.verifyCode(code)) {
            ((SettingsWizardActivity)getActivity()).nextTab();
            codeValid = true;
            return;
        }

        setCodeInvalid();
    }

    protected boolean verifyCode(int code)
    {
        if (code == ((SettingsWizardActivity)getActivity()).getVerificationCode()) {
            return  true;
        }
        return  false;
    }

    protected void setCodeInvalid()
    {
        lblMessage.setText(R.string.email_incorrect_code);
        lblMessage.setTextColor(getResources().getColor(R.color.colorErrorRed));
        txtCode.setText("");
    }

    @Override
    public boolean validateInput()
    {
        return codeValid;
    }

}
