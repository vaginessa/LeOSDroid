@file:Suppress("PackageDirectoryMismatch")
package nya.kitsunyan.foxydroid.utility.extension.resources

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.math.*

object TypefaceExtra {
  val medium = Typeface.create("sans-serif-medium", Typeface.NORMAL)!!
  val light = Typeface.create("sans-serif-light", Typeface.NORMAL)!!
}

fun Context.getColorFromAttr(attrResId: Int): ColorStateList {
  val typedArray = obtainStyledAttributes(intArrayOf(attrResId))
  val (colorStateList, resId) = try {
    Pair(typedArray.getColorStateList(0), typedArray.getResourceId(0, 0))
  } finally {
    typedArray.recycle()
  }
  return colorStateList ?: ContextCompat.getColorStateList(this, resId)!!
}

fun Context.getDrawableFromAttr(attrResId: Int): Drawable {
  val typedArray = obtainStyledAttributes(intArrayOf(attrResId))
  val resId = try {
    typedArray.getResourceId(0, 0)
  } finally {
    typedArray.recycle()
  }
  return ContextCompat.getDrawable(this, resId)!!
}

fun Resources.sizeScaled(size: Int): Int {
  return (size * displayMetrics.density).roundToInt()
}

fun TextView.setTextSizeScaled(size: Int) {
  val realSize = (size * resources.displayMetrics.scaledDensity).roundToInt()
  setTextSize(TypedValue.COMPLEX_UNIT_PX, realSize.toFloat())
}

fun ViewGroup.inflate(layoutResId: Int): View {
  return LayoutInflater.from(context).inflate(layoutResId, this, false)
}
