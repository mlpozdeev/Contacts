package com.mlpozdeev.entity

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