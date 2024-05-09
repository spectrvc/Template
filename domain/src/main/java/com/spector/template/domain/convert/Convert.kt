package com.spector.template.domain.convert

fun Boolean.toTemplateString(): String {
    return if (this) "1" else "0"
}

fun String.toTemplateBoolean(): Boolean {
    return (this == "1")
}

fun String.toTemplateInt(): Int {
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        0
    }
}

fun String.toTemplateDouble(): Double {
    return try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}

