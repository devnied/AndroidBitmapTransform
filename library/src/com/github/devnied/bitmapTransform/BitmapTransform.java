package com.github.devnied.bitmapTransform;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;

/**
 * Class used to provide methods for performing operations on bitmap For example
 * to MULTIPLY, LIGHTEN, XOR, ADD, DARKEN, CLEAR bitmap
 * 
 * @author Millau Julien
 */
public final class BitmapTransform {

	/**
	 * Method to get the bitmapOptions with preferedConfig ARGB_8888
	 * and bitmap mutable
	 * @return the bitmap options
	 */
	private static BitmapFactory.Options getBitmapOptions() {
		// Create bitmap options
		BitmapFactory.Options options = new BitmapFactory.Options();
		// for HC+, we load the bitmap as mutable
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			options.inMutable = true;
		}
		// We used ARGB_8888 (RGB and alpha for translucency) stored with 8 bits
		// of precision for better quality
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		return options;
	}

	/**
	 * Method to obtain an bitmap from resources Id
	 * 
	 * @param pContext Application context
	 * @param pResId resources Id
	 * @return Bitmap corresponding to the pResId or <code>null</code>
	 */
	public static Bitmap getBitmapResources(final Context pContext,
			final int pResId) {
		// Get Resources
		Resources res = pContext.getResources();
		Bitmap source = BitmapFactory.decodeResource(res, pResId,
				getBitmapOptions());

		// create a mutable bitmap if necessary
		Bitmap bitmap = null;
		if (source != null) {
			if (source.isMutable()) {
				bitmap = source;
			} else {
				bitmap = source.copy(Bitmap.Config.ARGB_8888, true);
				source.recycle();
			}

			// Enable alpha.
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
				bitmap.setHasAlpha(true);
			}
		}

		return bitmap;
	}

	/**
	 * Method to create a bitmap from two bitmap resources
	 * @param pContext Application context
	 * @param pDstId bitmap dest
	 * @param pSrcId bitmap src
	 * @param pMode PorterDuff mode 
	 * @param pScale true if we need to scale the src bitmap to the size of the dest bitmap
	 * @param pNewInstance boolean to indicate if this method can modify the Bitmap parameters
	 * @return the new bitmap
	 */
	public static Bitmap createBitmap(final Context pContext, final int pDstId,
			final int pSrcId, final PorterDuff.Mode pMode,
			final boolean pScale, final boolean pNewInstance) {
		return createBitmap(getBitmapResources(pContext, pDstId),
				getBitmapResources(pContext, pSrcId), pMode, pScale,
				pNewInstance);
	}

	/**
	 * Method to create a Bitmap with the specified mode
	 * @param pDst the first bitmap
	 * @param pSrc the second bitmap
	 * @param pMode the selected PorterDuff mode
	 * @param pNewInstance boolean to indicate if this method can modify the Bitmap parameters
	 * @return the created bitmap
	 */
	public static Bitmap createBitmap(final Bitmap pDst, final Bitmap pSrc,
			final PorterDuff.Mode pMode, final boolean pScale,
			final boolean pNewInstance) {
		Bitmap selected = pDst;
		// add the first image into a canvas
		Canvas canvas = null;
		if (pNewInstance) {
			selected = pDst.copy(Bitmap.Config.ARGB_8888, true);
		}
		canvas = new Canvas(selected);
		Paint paint = new Paint();
		// apply the PorterDuff mode operation
		paint.setXfermode(new PorterDuffXfermode(pMode));
		// scale the image
		Bitmap mask = null;
		if (pScale) {
			mask = Bitmap.createScaledBitmap(pSrc, selected.getHeight(),
					selected.getWidth(), true);
		} else {
			mask = pSrc;
		}
		canvas.drawBitmap(mask, 0, 0, paint);
		if (!pNewInstance) {
			// We do not need the mask bitmap and the second image anymore.
			mask.recycle();
			pSrc.recycle();
		}
		return selected;
	}

	/**
	 * Private Constructor
	 */
	private BitmapTransform() {
	}

}
