package com.epfl.neighborfood.neighborfoodandroid.authentication;

public class AuthenticatorFactory {


    private static Authenticator dependency = FirebaseAuthenticator.getInstance();

    /**
     * Gets the authenticator instance currently used
     *
     * @return authenticator
     */
    public static Authenticator getDependency() {
        return dependency;
    }

    /**
     * Sets the authenticator instance to be used
     *
     * @param authenticator authenticator
     */
    public static void setDependency(Authenticator authenticator) {
        dependency = authenticator;
    }
}
