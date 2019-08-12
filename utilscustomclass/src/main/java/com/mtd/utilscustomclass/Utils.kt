package com.mtd.utilscustomclass

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.util.AttributeSet
import android.view.MenuItem
import androidx.collection.SimpleArrayMap
import com.mtd.utilscustomclass.customClassViews.UtilsSpan
import java.text.SimpleDateFormat
import java.util.*

enum class TypefaceEnum {
    Normal, Light, Bold, En
}

class Utils {

    companion object {

        //<editor-fold desc="InitFont">

        private val cache = SimpleArrayMap<String, Typeface>()

        private fun setFontNormal(mContext: Context): Typeface {
            return getTypeFace(mContext, "RANYekanRegularMobile(FaNum)")
        }

        private fun setFontLight(mContext: Context): Typeface {
            return getTypeFace(mContext, "IRANYekanLightMobile(FaNum)")
        }

        private fun setFontBold(mContext: Context): Typeface {
            return getTypeFace(mContext, "IRANYekanMobileBold(FaNum)")
        }

        private fun setFontEn(mContext: Context): Typeface {
            return getTypeFace(mContext, "IRANYekanMobileRegular_en")
        }

        fun initFont(attrs: AttributeSet?, mContext: Context): Typeface? {
            return if (attrs != null) {
                val typedArray: TypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TextViewCustom)
                val fontName: Int? = typedArray.getInteger(R.styleable.TextViewCustom_fontName, 0)
                getStatusFonts(mContext, null, fontName)
            } else {
                setFontNormal(mContext)
            }
        }

        fun setMenuItemTypeFace(mContext: Context, menu: MenuItem, name: String = "") {
            val font = getTypeFace(mContext, name)
            val mNewTitle = SpannableString(menu.title)
            mNewTitle.setSpan(UtilsSpan("", font), 0, mNewTitle.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            menu.title = mNewTitle
        }


        fun setFont(mContext: Context, enumMap: TypefaceEnum): Typeface {
            return getStatusFonts(mContext, enumMap)
        }

        private fun getStatusFonts(mContext: Context, enumMap: TypefaceEnum? = null, idEnum: Int? = null): Typeface {
            return if (idEnum != null) {
                when (idEnum) {
                    0 -> setFontNormal(mContext)
                    1 -> setFontLight(mContext)
                    2 -> setFontBold(mContext)
                    3 -> setFontEn(mContext)
                    else -> setFontNormal(mContext)
                }
            } else {
                when (enumMap!!) {
                    TypefaceEnum.Normal -> setFontNormal(mContext)
                    TypefaceEnum.Light -> setFontLight(mContext)
                    TypefaceEnum.Bold -> setFontBold(mContext)
                    TypefaceEnum.En -> setFontEn(mContext)
                }

            }
        }

        private fun getTypeFace(context: Context, name: String): Typeface {
            synchronized(this) {
                if (!cache.containsKey(name)) {
                    val t: Typeface
                    t = try {
                        Typeface.createFromAsset(context.assets, String.format("fonts/%s.ttf", name))
                    } catch (e: Exception) {
                        val nameFont = context.assets.list("fonts")
                        Typeface.createFromAsset(context.assets, String.format("fonts/%s", nameFont[0]))
                    }
                    cache.put(name, t)
                    return t
                }
            }
            return cache.get(name)!!
        }

        //</editor-fold>

        //<editor-fold desc="Date">
        private fun dayBetween(dateOne: Date, dateTwo: Date): Long? {
            val different: Long = (dateOne.time - dateTwo.time) / 86400000
            return Math.abs(different)
        }

        private fun convertDate(sampleDate: Any?, status: String = "Parse"): Any? {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            return if (status == "Format")
            //return String
                dateFormat.format(sampleDate)
            else
            // return Date
                dateFormat.parse(sampleDate.toString())

        }

        fun parserDate(dataParser: String): String? {
            val calender = Calendar.getInstance()
            val finallyDate: String?

            //<editor-fold desc="ConvertDate">

            val currentDate = dataParser.substring(0, 19)

            val dateOld: Date? = convertDate(currentDate) as Date


            val dateNew = convertDate(convertDate(calender.time, "Format"))
            //</editor-fold>

            //<editor-fold desc="ConvertTime">
            val currentTime = dataParser.subSequence(11, 19)
            val timeOld = convertDate(currentDate) as Date
            val timeNew = calender.time
            //</editor-fold>

            val differentDay: Long? = dayBetween(dateNew as Date, dateOld as Date)

            when (differentDay) {
                0L -> {
                    var differentTime = timeNew.time - timeOld.time
                    val secondsInMilli = 1000
                    val minutesInMilli = secondsInMilli * 60
                    val hoursInMilli = minutesInMilli * 60
                    val daysInMilli = hoursInMilli * 24

                    differentTime %= daysInMilli

                    val elapsedHours = differentTime / hoursInMilli
                    differentTime %= hoursInMilli

                    val elapsedMinutes = differentTime / minutesInMilli

                    finallyDate = when {
                        elapsedMinutes == 0L && elapsedHours == 0L -> "همین الان"
                        elapsedHours == 0L -> "$elapsedMinutes دقیقه پیش "
                        else -> currentTime.substring(0, 5)
                    }

                }
                1L -> finallyDate = "دیروز"
                else -> finallyDate = "$differentDay روز پیش "
            }

            return finallyDate


        }
        //</editor-fold>

    }


}