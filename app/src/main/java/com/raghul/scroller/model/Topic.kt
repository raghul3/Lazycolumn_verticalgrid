package com.raghul.scroller.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
   @StringRes val stringresource:Int,
    val number:Int,
    @DrawableRes val picresource:Int
)
