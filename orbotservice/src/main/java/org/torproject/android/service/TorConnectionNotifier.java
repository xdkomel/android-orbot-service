package org.torproject.android.service;

import androidx.annotation.Nullable;

public class TorConnectionNotifier {

    @Nullable
    private static TorConnectionListener torListener;


    public static void notify(TorConnectionStatus status) {
        if (torListener != null) {
            torListener.onEvent(status.name());
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
