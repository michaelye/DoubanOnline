package com.michael.doubanonline.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;

/**
 *
 * 用来将一张尺寸不规则的图片裁剪成正方形的图片
 *
 * @author Michael
 * @version create time：2013-3-23 下午6:40:33
 */
//public class RectBitmapDisplayer implements BitmapDisplayer {
//
//
//	public RectBitmapDisplayer() {
//		
//		//do nothing
//	}
//
//	@Override
//	public Bitmap display(Bitmap bitmap, ImageView imageView) {
//		Bitmap roundBitmap;
//		try {
//			roundBitmap = getRectBitmap(bitmap);
//		} catch (OutOfMemoryError e) {
//			L.e(e, "Can't create Rect bitmap. Not enough memory.");
//			roundBitmap = bitmap;
//		}
//		imageView.setImageBitmap(roundBitmap);
//		return roundBitmap;
//	}
//	
//	private Bitmap getRectBitmap(Bitmap bitmap) {
//		
//		int x;
//		int y;
//		int width;
//		int height;
//		
//		int bmWidth = bitmap.getWidth();
//		int bmHeight = bitmap.getHeight();
//		
//		if(bmHeight == bmWidth){//如果宽高相等，直接返回
//			
//			return bitmap;
//		}else if(bmHeight > bmWidth){//如果高大于宽
//			
//			//x:0 y:(高-宽)/2 width:宽 height:宽
//			x = 0;
//			y = (bmHeight - bmWidth) / 2;
//			width = bmWidth;
//			height = bmWidth;
//		}else{//如果高小于宽
//			
//			//x:(宽-高)/2 y:0 width:高 height:高
//			x = (bmWidth - bmHeight) / 2;
//			y = 0;
//			width = bmHeight;
//			height = bmHeight;
//		}
//		Bitmap output = Bitmap.createBitmap(bitmap, x, y, width, height);
//				
//		return output;
//	}
//
//}
