package com.lamadmiralis.bettercardgame.utility;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;

import com.lamadmiralis.bettercardgame.objects.card.CardTemplate;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maczaka
 */
public class ImageHolder {

    public static final int IMG_CARD_PIC_WIDTH = 365;
    public static final int IMG_CARD_PIC_HEIGHT = 245;
    private static final Map<String, Bitmap> CARD_CACHE = new HashMap<>();
    private static final SparseArray<Bitmap> RES_CACHE = new SparseArray<>();
    private static final SparseArray<Paint> NUMBER_CACHE = new SparseArray<>();
    private static final float RESIZE_RATIO = 720 / 140;
    private static final float RESIZE_RATIO_FINAL = 4.5F;
    private static Paint font = new Paint();
    private static boolean fontInitiated = false;

    public static Bitmap initResource(final Context context, final int id) {
        final Resources resources = context.getResources();
        final Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
        RES_CACHE.put(id, bitmap);
        return bitmap;
    }

    public static Bitmap resizeBitmap(final Bitmap originalBitmap, final float resizeRatio) {
        return resizeBitMap(originalBitmap, resizeRatio, resizeRatio);
    }

    public static Bitmap resizeBitMap(final Bitmap original, final float ratioW, final float ratioH) {
        return Bitmap.createScaledBitmap(original,
                (int) (original.getWidth() / ratioW),
                (int) (original.getHeight() / ratioH),
                false);
    }

    public static void initCardImage(final CardTemplate card) {
        Bitmap image = getFullCardImageByName(card.getName());
        if (image == null) {
            image = Bitmap.createBitmap((ProjectConstants.FULL_CARD_WIDTH),
                    (ProjectConstants.FULL_CARD_HEIGHT),
                    Bitmap.Config.ARGB_8888
            );
            final Canvas canvas = new Canvas(image);
            //canvas.drawBitmap(getResById(card.getFront()), 0, 0, null);
            final Bitmap smallImage = getResById(card.getImageId());
            CARD_CACHE.put(card.getName() + "#smallSize", resizeBitmap(smallImage, RESIZE_RATIO_FINAL));
            canvas.drawBitmap(smallImage,
                    0,
                    0,
                    null
            );
            //canvas.drawBitmap(getResById(card.getFrame()), 0, 0, null);
            //canvas.drawBitmap(getResById(card.getBanner()), 0, 0, null);
            canvas.drawText(card.getName(), 300 / 2, 40, getBasicFont());
            CARD_CACHE.put(card.getName() + "#fullSize", resizeBitmap(image, RESIZE_RATIO_FINAL));
        }
    }

    public static Bitmap getResById(final int id) {
        return RES_CACHE.get(id);
    }

    public static Bitmap getResByIdAndResize(final int id, final float ratio) {
        return resizeBitmap(RES_CACHE.get(id), ratio);
    }

    public static Paint getBasicFont() {
        if (!fontInitiated) {
            font.setTypeface(Typeface.DEFAULT_BOLD);
            font.setTextSize(40);
            font.setTextAlign(Paint.Align.CENTER);
            fontInitiated = true;
        }
        return font;
    }

    public static Bitmap getResizedCardBack() {
        Bitmap cardBack = CARD_CACHE.get("cardBack#Small");
        if (cardBack == null) {
            cardBack = resizeBitmap(getResById(ProjectConstants.DEFAULT_CARD_BACK), RESIZE_RATIO_FINAL);
            CARD_CACHE.put("cardBack#Small", cardBack);
        }
        return cardBack;
    }

    private static Bitmap resizeCardImageToFitFrame(final Bitmap image) {
        final int resizedCardWidth = (int) (IMG_CARD_PIC_WIDTH / RESIZE_RATIO);
        final int resizedCardHeight = (int) (IMG_CARD_PIC_HEIGHT / RESIZE_RATIO);
        return Bitmap.createScaledBitmap(image,
                resizedCardWidth,
                resizedCardHeight,
                false);
    }

    public static Bitmap getFullCardImageByName(final String name) {
        return CARD_CACHE.get(name + "#fullSize");
    }

    public static Bitmap getSmallCardImageByName(final String name) {
        return CARD_CACHE.get(name + "#smallSize");
    }

    public static Paint getNumberText(final int number) {
        Paint paint = NUMBER_CACHE.get(number);
        if (paint == null) {
            paint = new Paint();
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.CENTER);
            NUMBER_CACHE.put(number, paint);
        }
        return paint;
    }

    public static Paint getRecolouredNumberText(final int currentValue, final int originalValue) {
        final Paint paint = getNumberText(currentValue);
        if (currentValue > originalValue) {
            paint.setColor(ContextCompat.getColor(InterfaceContext.getInstance().getContext(),
                    ProjectConstants.COLOR_ABOVE_ORIGINAL));
        } else if (currentValue < originalValue) {
            paint.setColor(ContextCompat.getColor(InterfaceContext.getInstance().getContext(),
                    ProjectConstants.COLOR_DAMAGED));
        }
        return paint;
    }
}
