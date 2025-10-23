package com.benyq.core.extentions


inline fun Boolean?.ifTrue(block: () -> Unit) {
    if (this == true) {
        block()
    }
}

inline fun Boolean?.ifFalse(block: () -> Unit) {
    if (this == false) {
        block()
    }
}
