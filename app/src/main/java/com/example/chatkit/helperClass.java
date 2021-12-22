package com.example.chatkit;

public class helperClass {

    String fullN, emailA, phoneN, pass, confirmP;

    public helperClass() {

    }

    public helperClass(String fullN, String emailA, String phoneN, String pass, String confirmP) {

        this.fullN = fullN;
        this.emailA = emailA;
        this.phoneN = phoneN;
        this.pass = pass;
        this.confirmP = confirmP;
    }

    public String getFullN() {
        return fullN;
    }

    public void setFullN(String fullN) {
        this.fullN = fullN;
    }

    public String getEmailA() {
        return emailA;
    }

    public void setEmailA(String emailA) {
        this.emailA = emailA;
    }

    public String getPhoneN() {
        return phoneN;
    }

    public void setPhoneN(String phoneN) {
        this.phoneN = phoneN;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmP() {
        return confirmP;
    }

    public void setConfirmP(String confirmP) {
        this.confirmP = confirmP;
    }
}
