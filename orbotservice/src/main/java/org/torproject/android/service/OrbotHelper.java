package org.torproject.android.service;

import org.torproject.android.ui.v3onionservice.clientauth.ClientAuthContentProvider;

public class OrbotHelper {

    public static String applicationId = "";

    public static void setApplicationId(String value) {
        applicationId = value;
        ClientAuthContentProvider.initialize(value);
    }
}
