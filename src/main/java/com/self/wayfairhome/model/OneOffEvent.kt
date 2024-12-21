package com.self.wayfairhome.model

sealed class OneOffEvent {
    class ShowError(val message: String) : OneOffEvent()
}