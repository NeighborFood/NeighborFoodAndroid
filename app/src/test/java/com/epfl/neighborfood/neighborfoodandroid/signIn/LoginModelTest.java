package com.epfl.neighborfood.neighborfoodandroid.signIn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import com.epfl.neighborfood.neighborfoodandroid.login.LoginModel;
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleLoginHandler;

import org.junit.Test;

public class LoginModelTest {

    @Test
    public void loginHandlerGetterReturnCorrectValue(){
        GoogleLoginHandler glh = new GoogleLoginHandler();
        LoginModel loginModel = new LoginModel(null, glh, null);
        assertThat(loginModel.getLoginHandler(), equalTo(glh));
    }
}
