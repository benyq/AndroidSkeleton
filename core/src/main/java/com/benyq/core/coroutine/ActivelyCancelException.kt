package com.benyq.core.coroutine

import kotlin.coroutines.cancellation.CancellationException

class ActivelyCancelException : CancellationException()