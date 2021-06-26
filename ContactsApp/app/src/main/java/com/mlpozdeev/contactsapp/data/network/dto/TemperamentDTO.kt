package com.mlpozdeev.contactsapp.data.network.dto

import com.google.gson.annotations.SerializedName

enum class TemperamentDTO {
    @SerializedName("melancholic")
    MELANCHOLIC,

    @SerializedName("phlegmatic")
    PHLEGMATIC,

    @SerializedName("sanguine")
    SANGUINE,

    @SerializedName("choleric")
    CHOLERIC
}
