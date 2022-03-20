package com.epfl.neighborfood.neighborfoodandroid.login;

import android.content.Intent;

public interface LoginHandler {

    Account handleOnLoginIntentResult(int requestCode, Intent intent);
}
