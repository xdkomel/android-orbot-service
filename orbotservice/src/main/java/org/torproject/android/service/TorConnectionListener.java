package org.torproject.android.service;

public interface TorConnectionListener {
    void onEvent(String message);

    void onLog(String message);

    void onBandwidthUpdate(long read, long written);
}
