package org.torproject.android.service;

public interface TorConnectionListener {
    void onEvent(String message);

    void onLog(String message);
}
