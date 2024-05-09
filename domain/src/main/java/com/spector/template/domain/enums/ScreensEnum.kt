package com.spector.template.domain.enums

import com.spector.template.domain.const.ConstOptions.Companion.OPTIONS

enum class ScreensEnum(val value: String){
    ABOUT("ABOUT"),
    DIVIDER("DIVIDER"),
    REF_ACCOUNTS("REF_ACCOUNTS"),
    REF_ACCOUNTS_DIALOG("REF_ACCOUNTS_DIALOG/$OPTIONS={$OPTIONS}"),
    SETTINGS("SETTINGS"),
    TITLE("TITLE"),
}

