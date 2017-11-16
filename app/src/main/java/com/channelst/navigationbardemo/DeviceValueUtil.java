package com.channelst.navigationbardemo;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Luojingjing on 2017/11/8.
 */

public class DeviceValueUtil {
    /**
     * 获取实际宽度
     */
    public static int getRealityWidthPx(Context context, int designWidth){
        int realityScreenWidth = DeviceValueUtil.getScreenWidthPx(context);
        return Math.round(realityScreenWidth * designWidth / (float) Constants.DESIGN_SCREEN_WIDTH);
    }

    /**
     * 获取实际高度
     */
    public static int getRealityHeightPx(Context context, int desingHeight){
        int realityScreenHeight = DeviceValueUtil.getScreenHeightPx(context);
        return Math.round(realityScreenHeight * desingHeight
                / (float) Constants.DESIGN_SCREEN_HEIGHT);

    }

    /**
     * get screen Height in Px
     * @param context
     * @return
     */
    public static int getScreenHeightPx(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * get screen Width in Px
     * @param context
     * @return
     */
    public static int getScreenWidthPx(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

}
