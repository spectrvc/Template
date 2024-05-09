package com.spector.template.domain.enums

enum class LanguageEnum(val value: String) {
    ENGLISH("en"),
    RUSSIAN("ru"),
    UKRAINIAN("uk");

    companion object {
        infix fun from(value: String): LanguageEnum {
            var enum = LanguageEnum.values().firstOrNull { it.value == value }
            if (enum == null)
                enum = UKRAINIAN
            return enum
        }
    }
}
