package utils;

import android.graphics.drawable.Drawable;

/**
 * User: Андрей
 * Date: 15.10.13
 */
public class ImageHelper {

    public static Drawable getDrawableFromUri(String pPatch){
        return Drawable.createFromPath(pPatch);
    }

}
