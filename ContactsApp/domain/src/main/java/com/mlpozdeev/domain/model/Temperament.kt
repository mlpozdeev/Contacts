package com.mlpozdeev.domain.model

enum class Temperament {
    MELANCHOLIC,
    PHLEGMATIC,
    SANGUINE,
    CHOLERIC;

    override fun toString(): String {
        return super.toString().lowercase().replaceFirstChar {
            it.uppercaseChar()
        }
    }
}