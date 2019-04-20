package com.helpnet.tech.util

object Constants {
    const val OS_NUMBER_PARAM = "osNumberParam"
    const val OS_WIP_PARAM = "osWipParam"


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