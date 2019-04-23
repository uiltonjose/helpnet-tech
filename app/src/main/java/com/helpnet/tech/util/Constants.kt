package com.helpnet.tech.util

object Constants {
    const val OS_NUMBER_PARAM = "osNumberParam"
    const val OS_WIP_PARAM = "osWipParam"
    const val DEFAULT_LOGO =
        "https://firebasestorage.googleapis.com/v0/b/helpnet-3a519.appspot.com/o/provider%2Flogo-default.png?alt=media&token=9fa874f2-d830-460e-8980-1faf3b763850"

}

enum class Situations(val value: Int) {
    OPEN(1),
    WORK_IN_PROGRESS(2),
    CLOSE(3),
    CONCLUDED(4)
}

enum class EventType(val value: Int) {
    OPEN_OS(1),
    PUT_IN_PROGRESS(2),
    PUT_OFF(3),
    CLOSE_OS(4),
    CHANGE_TECHNICAL(5)
}