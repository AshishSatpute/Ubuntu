package com.xlix.basuons;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static URLhelper.BaseHelper.KEY_ACCNAME;
import static URLhelper.BaseHelper.KEY_ACCNO;
import static URLhelper.BaseHelper.KEY_ADDONE;
import static URLhelper.BaseHelper.KEY_ADDTWO;
import static URLhelper.BaseHelper.KEY_ADHARNO;
import static URLhelper.BaseHelper.KEY_BRANCHNAME;
import static URLhelper.BaseHelper.KEY_CITY;
import static URLhelper.BaseHelper.KEY_COMAMT;
import static URLhelper.BaseHelper.KEY_COUNTRY;
import static URLhelper.BaseHelper.KEY_CREATEDAT;
import static URLhelper.BaseHelper.KEY_EMAIL;
import static URLhelper.BaseHelper.KEY_GENDER;
import static URLhelper.BaseHelper.KEY_ID;
import static URLhelper.BaseHelper.KEY_IFSCCODE;
import static URLhelper.BaseHelper.KEY_KYC;
import static URLhelper.BaseHelper.KEY_MOBILE;
import static URLhelper.BaseHelper.KEY_NAME;
import static URLhelper.BaseHelper.KEY_NOUNITS;
import static URLhelper.BaseHelper.KEY_PANNO;
import static URLhelper.BaseHelper.KEY_PASSWORD;
import static URLhelper.BaseHelper.KEY_PINCODE;
import static URLhelper.BaseHelper.KEY_REFERALCODE;
import static URLhelper.BaseHelper.KEY_REFEREDCODE;
import static URLhelper.BaseHelper.KEY_STATE;
import static URLhelper.BaseHelper.KEY_STATUS;
import static URLhelper.BaseHelper.KEY_TYPE;
import static URLhelper.BaseHelper.KEY_UPDATEDAT;
import static URLhelper.BaseHelper.KEY_WALLETAMT;
import static URLhelper.BaseHelper.KEY_WHATAPP;
import static URLhelper.BaseHelper.KEY_WINNINGAMT;
import static URLhelper.BaseHelper.PREFS_NAME;
import static URLhelper.BaseHelper.PREFS_NAME2;
import static URLhelper.BaseHelper.IS_LOGIN;

class SessionManagement {
    SharedPreferences prefs;
    SharedPreferences prefs2;

    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;

    Context context;

    int PRIVATE_MODE = 0;


    public SessionManagement(Context context) {

        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = prefs.edit();

        prefs2 = context.getSharedPreferences(PREFS_NAME2, PRIVATE_MODE);
        editor2 = prefs2.edit();

    }


    public void checkLogin() {

        if (!this.isLoggedIn()) {
            Intent loginsucces = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            loginsucces.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            loginsucces.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(loginsucces);
        }
    }
    public boolean isLoggedIn() {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();

        Intent logout = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(logout);
    }

    public void logoutSessionwithchangepassword() {
        editor.clear();
        editor.commit();

        Intent logout = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(logout);
    }

    public void createLoginSession(String id, String full_name, String mobile, String email, String type,String status1, String created_at, String updated_at, String ifsc_code, String acc_name, String acc_no, String acc_branchname, String address_one, String address_two, String city, String state, String country, String password, String pincode, String wallet_amount, String winning_amount, String comission_amount, String no_of_units, String referral_code, String refered_code, String kyc_status, String aadharno, String panno, String whats_app_no, String gender) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, full_name);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TYPE, type);
        editor.putString(KEY_STATUS, status1);
        editor.putString(KEY_CREATEDAT, created_at);
        editor.putString(KEY_UPDATEDAT, updated_at);
        editor.putString(KEY_IFSCCODE, ifsc_code);
        editor.putString(KEY_ACCNAME, acc_name);
        editor.putString(KEY_ACCNO, acc_no);
        editor.putString(KEY_BRANCHNAME, acc_branchname);
        editor.putString(KEY_ADDONE, address_one);
        editor.putString(KEY_ADDTWO, address_two);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_STATE, state);
        editor.putString(KEY_COUNTRY, country);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_PINCODE, pincode);
        editor.putString(KEY_WALLETAMT, wallet_amount);
        editor.putString(KEY_WINNINGAMT, winning_amount);
        editor.putString(KEY_COMAMT, comission_amount);
        editor.putString(KEY_NOUNITS, no_of_units);
        editor.putString(KEY_REFERALCODE, referral_code);
        editor.putString(KEY_REFEREDCODE, refered_code);
        editor.putString(KEY_KYC, kyc_status);
        editor.putString(KEY_ADHARNO, aadharno);
        editor.putString(KEY_PANNO, panno);
        editor.putString(KEY_WHATAPP,whats_app_no);
        editor.putString(KEY_GENDER,gender);

        editor.commit();
    }
}
