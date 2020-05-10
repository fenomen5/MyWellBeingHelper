package com.barkov.ais.mywellbeinghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAccountStep1;
    EditText txtName, txtSurname, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        btnAccountStep1 = (Button)findViewById(R.id.btnAccountStep1);
        btnAccountStep1.setOnClickListener(this);

        txtName = findViewById(R.id.txtSearchParamClubName);
        txtSurname = findViewById(R.id.txtSurname);
        txtEmail = findViewById(R.id.txtEmail);
    }

    @Override
    public void onClick(View v) {
        if (validatePersonalDetails()) {
            EmailHelper emailHelper = new EmailHelper();
            emailHelper.send(
                    "dwfvc15@kmail.it",
                    "Email check",
                    "Your verification code is " + 345 );
        }
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

}
