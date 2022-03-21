package com.epfl.neighborfood.neighborfoodandroid.login;

import android.content.Intent;

public interface LoginHandler {

    /**
     *
     * @param requestCode(int): identifier to check the source of the intent
     * @param intent(Intent): the intent that the function will use to extract the Login account
     * @return the signed in account as a result of the intent
     */
    Account handleOnLoginIntentResult(int requestCode, Intent intent);
}
