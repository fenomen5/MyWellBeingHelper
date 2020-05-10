package com.barkov.ais.mywellbeinghelper;

import android.util.Log;

import com.barkov.ais.mywellbeinghelper.mail.GMailSender;

public class EmailHelper {

    public boolean send(final String recipient, final  String subject, final String body)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("antnorv95@gmail.com",
                            "norv95gmail");
                    sender.sendMail(subject, body,
                            "antnorv95@gmail.com", recipient);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
        return true;
    }
}
