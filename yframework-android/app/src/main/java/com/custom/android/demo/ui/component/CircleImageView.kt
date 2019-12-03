package com.custom.android.demo.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.NinePatchDrawable
import android.util.AttributeSet
import android.widget.ImageView
import com.custom.android.demo.R

/**
 * Description: CircleImageView<br>
 * Comments Name: CircleImageView<br>
 * Date: 2019-09-17 18:44<br>
 * Author: yyl<br>
 * EditorDate: 2019-09-17 18:44<br>
 * Editor: yyl
 */
class CircleImageView : ImageView {
    private var mContext: Context? = null
    // 如果只有其中一个有值，则只画一个圆形边框
    private var mBorderOutsideColor = 0
    private var mBorderInsideColor = 0
    private var mBorderThickness = 0
    // 控件默认长、宽
    private var defaultWidth = 0
    private var defaultHeight = 0
    private val defaultColor = -0x1

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        setCustomAttributes(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        mContext = context
        setCustomAttributes(attrs)
    }

    @SuppressLint("Recycle")
    private fun setCustomAttributes(attrs: AttributeSet) {
        val a = mContext!!.obtainStyledAttributes(
            attrs,
            R.styleable.CircleImageView
        )
        mBorderThickness = a.getDimensionPixelSize(
            R.styleable.CircleImageView_border_thickness, 0
        )
        mBorderOutsideColor = a.getColor(
            R.styleable.CircleImageView_border_outside_color, defaultColor
        )
        mBorderInsideColor = a.getColor(
            R.styleable.CircleImageView_border_inside_color, defaultColor
        )
    }

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return
        if (width == 0 || height == 0) {
            return
        }
        this.measure(0, 0)
        if (drawable.javaClass == NinePatchDrawable::class.java)
            return
        val b: Bitmap?
        b = (drawable as BitmapDrawable).bitmap
        if (b == null) return
        if (defaultWidth == 0) {
            defaultWidth = width
        }
        if (defaultHeight == 0) {
            defaultHeight = height
        }
        val radius: Int
        if (mBorderInsideColor != defaultColor && mBorderOutsideColor != defaultColor) {// 定义画两个边框，分别为外圆边框和内圆边框
            radius = (if (defaultWidth < defaultHeight)
                defaultWidth
            else
                defaultHeight) / 2 - 2 * mBorderThickness
            // 画内圆
            drawCircleBorder(
                canvas, radius + mBorderThickness / 2,
                mBorderInsideColor
            )
            // 画外圆
            drawCircleBorder(
                canvas, radius + mBorderThickness
                        + mBorderThickness / 2, mBorderOutsideColor
            )
        } else if (mBorderInsideColor != defaultColor) {// 定义画一个边框
            radius = (if (defaultWidth < defaultHeight)
                defaultWidth
            else
                defaultHeight) / 2 - mBorderThickness
            drawCircleBorder(
                canvas, radius + mBorderThickness / 2,
                mBorderInsideColor
            )
        } else if (mBorderOutsideColor != defaultColor) {// 定义画一个边框
            radius = (if (defaultWidth < defaultHeight)
                defaultWidth
            else
                defaultHeight) / 2 - mBorderThickness
            drawCircleBorder(
                canvas, radius + mBorderThickness / 2,
                mBorderOutsideColor
            )
        } else {// 没有边框
            radius = (if (defaultWidth < defaultHeight)
                defaultWidth
            else
                defaultHeight) / 2
        }
        val roundBitmap = getCroppedRoundBitmap(b, radius)
        canvas.drawBitmap(
            roundBitmap,
            (defaultWidth / 2 - radius).toFloat(),
            (defaultHeight / 2 - radius).toFloat(),
            null
        )
    }

    /**
     * 获取裁剪后的圆形图片
     *
     * @param bmp
     * @param radius radius半径
     * @return
     */
    fun getCroppedRoundBitmap(bmp: Bitmap, radius: Int): Bitmap {
        val scaledSrcBmp: Bitmap?
        val squareBitmap: Bitmap?
        val output: Bitmap
        val diameter = radius * 2
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        val bmpWidth = bmp.width
        val bmpHeight = bmp.height
        val squareWidth: Int
        val squareHeight: Int
        var x = 0
        val y: Int

        if (bmpHeight > bmpWidth) {// 高大于宽
            squareHeight = bmpWidth
            squareWidth = squareHeight
            y = (bmpHeight - bmpWidth) / 2
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(
                bmp, x, y, squareWidth,
                squareHeight
            )
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareHeight = bmpHeight
            squareWidth = squareHeight
            x = (bmpWidth - bmpHeight) / 2
            y = 0
            squareBitmap = Bitmap.createBitmap(
                bmp, x, y, squareWidth,
                squareHeight
            )
        } else {
            squareBitmap = bmp
        }
        if (squareBitmap!!.width != diameter || squareBitmap.height != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(
                squareBitmap, diameter,
                diameter, true
            )
        } else {
            scaledSrcBmp = squareBitmap
        }
        output = Bitmap.createBitmap(
            scaledSrcBmp!!.width,
            scaledSrcBmp.height, Bitmap.Config.ARGB_4444
        )
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(
            0, 0, scaledSrcBmp.width,
            scaledSrcBmp.height
        )

        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(
            (scaledSrcBmp.width / 2).toFloat(),
            (scaledSrcBmp.height / 2).toFloat(), (scaledSrcBmp.width / 2).toFloat(),
            paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint)
        return output
    }

    /**
     * 边缘画圆
     */
    private fun drawCircleBorder(canvas: Canvas, radius: Int, color: Int) {
        val paint = Paint()
        /* 去锯齿 */
        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true
        paint.color = color
        /* 设置paint的　style　为STROKE：空心 */
        paint.style = Paint.Style.STROKE
        /* 设置paint的外框宽度 */
        paint.strokeWidth = mBorderThickness.toFloat()
        canvas.drawCircle(
            (defaultWidth / 2).toFloat(),
            (defaultHeight / 2).toFloat(),
            radius.toFloat(),
            paint
        )
    }
}