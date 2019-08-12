package com.mtd.utilscustomclass.customClassViews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import com.mtd.utilscustomclass.TypefaceEnum
import com.mtd.utilscustomclass.Utils.Companion.initFont
import com.mtd.utilscustomclass.Utils.Companion.setFont

class UtilsEditText : AppCompatEditText {
    constructor(mContext: Context) : super(mContext) {
        initFont(null, mContext)
    }

    constructor(mContext: Context, attrs: AttributeSet) : super(mContext, attrs) {
        this.typeface = initFont(attrs, mContext)
    }

    constructor(mContext: Context, attrs: AttributeSet, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) {
        this.typeface = initFont(attrs, mContext)
    }


    fun setTypeface(enum: TypefaceEnum) {
        this.typeface = setFont(context, enum)
    }
}