package com.parm.eventmaker;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class Email {

    private String email;
    private int check;

    Email(String email, int check)
    {
        this.email = email;
        this.check = check;
    }

    public String getEmail()
    {
        return email;
    }

    public int getCheck()
    {
        return check;
    }
}
