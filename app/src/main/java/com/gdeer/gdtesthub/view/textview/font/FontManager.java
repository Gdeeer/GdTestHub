package com.gdeer.gdtesthub.view.textview.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:    shaw
 * Version    V1.0
 * Date:      2017/7/11
 * Description:
 * Modification  History:
 * Date          Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/11       shaw             1.0
 * Why & What is modified:
 */
public class FontManager {
    public static final String DEFAULT_TYPE_FONT = "hel.ttf";

    private static FontManager instance;

    private AssetManager mgr;

    private Map<String, Typeface> fonts;

    private FontManager(AssetManager _mgr) {
        mgr = _mgr;
        fonts = new HashMap<String, Typeface>();
    }

    public static void init(AssetManager mgr) {
        instance = new FontManager(mgr);
    }

    public static FontManager getInstance(Context context) {

        if(instance == null){

            init(context.getAssets());
        }

        return instance;
    }

    public Typeface getDefaultFont() {
        return getFont(DEFAULT_TYPE_FONT);
    }

    public Typeface getFont(String asset) {
        if (fonts.containsKey(asset))
            return fonts.get(asset);

        Typeface font = null;

        try {
            font = Typeface.createFromAsset(mgr, asset);
            fonts.put(asset, font);
        } catch (Exception e) {

        }

        if (font == null) {
            try {
                String fixedAsset = fixAssetFilename(asset);
                font = Typeface.createFromAsset(mgr, fixedAsset);
                fonts.put(asset, font);
                fonts.put(fixedAsset, font);
            } catch (Exception e) {

            }
        }

        return font;
    }

    private String fixAssetFilename(String asset) {
        // Empty font filename?
        // Just return it. We can't help.
        if (TextUtils.isEmpty(asset))
            return asset;

        // Make sure that the font ends in '.ttf' or '.ttc'
        if ((!asset.endsWith(".ttf")) && (!asset.endsWith(".ttc")))
            asset = String.format("%s.ttf", asset);

        return asset;
    }
}
