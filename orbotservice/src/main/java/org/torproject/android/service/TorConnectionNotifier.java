package org.torproject.android.service;

import androidx.annotation.Nullable;

public class TorConnectionNotifier {
    public static String NOT_CONNECTED = "NOT_CONNECTED";
    public static String CONNECTING = "CONNECTING";
    public static String CONNECTED = "CONNECTED";
    public static String DISCONNECTING = "DISCONNECTING";

    @Nullable
    private static TorConnectionListener torListener;


    public static void notify(String message) {
        if (torListener != null) {
            torListener.onEvent(message);
        }
    }

    public static void notifyLog(String message) {
        if (torListener != null) {
            torListener.onLog(message);
        }
    }

    public static void setTorListener(@Nullable TorConnectionListener listener) {
        torListener = listener;
    }


}
