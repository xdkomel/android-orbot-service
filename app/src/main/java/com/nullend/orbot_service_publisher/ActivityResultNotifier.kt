package com.nullend.orbot_service_publisher

class ActivityResultNotifier {
    companion object {
        private val listeners = mutableListOf<(Int) -> Unit>()

        fun notify(resultCode: Int) {
            for (listener in listeners) listener(resultCode)
        }

        fun addListener(listener: (Int) -> Unit) {
            listeners.add(listener)
        }

        fun removeListener(listener: (Int) -> Unit) {
            listeners.remove(listener)
        }
    }
}