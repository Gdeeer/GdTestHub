package com.gdeer.gdtesthub.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 设备信息工具类
 * Created by jiangyuan on 2017/7/12.
 */

public class DeviceUtil {
    public static final boolean ATLEAST_KITKAT =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    private static final String MIUI_VERSION_NAME_PROP_KEY = "ro.miui.ui.version.name";

    /**
     * 获取屏幕高(像素)
     * 在全面屏上调用会有问题，如小米 mix 上，虚拟导航栏存不存在都会返回同一个值（物理屏幕高度-导航栏高度）
     */
    public static int getMobileHeight(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Desc: 获取屏幕真实高度
     */
    public static int getRealScreenHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }
        int realHeight = 0;
        try {
            Display display = activity.getWindowManager().getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                final DisplayMetrics metrics = new DisplayMetrics();
                display.getRealMetrics(metrics);
                realHeight = metrics.heightPixels;
            } else {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                realHeight = (Integer) mGetRawH.invoke(display);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realHeight;
    }

    /**
     * 获取屏幕内容高度，不包括虚拟导航栏
     */
    public static int getContentHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }

        View decorView = null;
        try {
            decorView = activity.getWindow().getDecorView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decorView == null) {
            return 0;
        }

        // 使用 DecorView 而不使用屏幕宽高，因为底部导航栏的高度比较难处理
        View contentView = decorView.findViewById(android.R.id.content);

        if (contentView == null) {
            return 0;
        }
        return contentView.getHeight();
    }

    /**
     * 获取屏幕宽(像素)
     */
    public static int getMobileWidth(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 是否是全面屏手机
     */
    public static boolean isLongScreenDevice(Context context) {
        int height = getMobileHeight(context);
        int width = getMobileWidth(context);
        return height * 1f / width > 1.8;
    }

    public static DisplayMetrics getDisplaySize(Context context) {
        if (context == null) {
            return null;
        }
        try {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取屏幕分辨率
    public static float getDisplaySizeDensity(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics.density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getDisplaySizeDensityString(Context context) {
        float density = getDisplaySizeDensity(context);
        return density == 0 ? "" : String.valueOf(density);
    }


    public static int getDisplaySizeDensityDpi(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            DisplayMetrics mDisplayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
            return mDisplayMetrics.densityDpi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // 获取内存大小
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static long getTotalMemory(Context c) {
        long DEVICEINFO_UNKNOWN = 0;
        // memInfo.totalMem not supported in pre-Jelly Bean APIs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
            ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
            am.getMemoryInfo(memInfo);
            if (memInfo != null) {
                return memInfo.totalMem;
            } else {
                return DEVICEINFO_UNKNOWN;
            }
        } else {
            long totalMem = DEVICEINFO_UNKNOWN;
            try {
                FileReader fr = new FileReader("/proc/meminfo");
                BufferedReader br = new BufferedReader(fr, 2048);
                String memoryLine = br.readLine();
                String subMemoryLine = "";
                if (memoryLine != null && memoryLine.contains("MemTotal:")) {
                    subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
                }
                br.close();
                if (!TextUtils.isEmpty(subMemoryLine)) {
                    return Integer.parseInt(subMemoryLine.replaceAll("\\D+", "")) * 1024L;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            return totalMem;
        }
    }

    public static int getNavigationBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static int getNavigationBarWidth(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_width", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getNavigationBarHeithtLandscape(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height_landscape", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 判断该手机是否是MIUI系统
    public static boolean isMiuiPhone(Context context) {
        String miuiVersionName = getMiuiVersionName(context);

        if (!miuiVersionName.equalsIgnoreCase("NULL")) {
            return true;
        }
        return false;
    }

    // 判断是否是MIUI5
    public static boolean isMiui5(Context context) {
        String miuiVersionName = getMiuiVersionName(context);
        if (miuiVersionName.equalsIgnoreCase("V5")) {
            return true;
        }

        return false;
    }

    // 判断是否是MIUI6
    public static boolean isMiui6(Context context) {
        String miuiVersionName = getMiuiVersionName(context);
        if (miuiVersionName.equalsIgnoreCase("V6")) {
            return true;
        }

        return false;
    }

    // 判断是否是MIUI7
    public static boolean isMiui7(Context context) {
        String miuiVersionName = getMiuiVersionName(context);
        if (miuiVersionName.equalsIgnoreCase("V7")) {
            return true;
        }

        return false;
    }

    public static boolean isTianYu() {
        if ("K-Touch T619+".equals(Build.MODEL)) {
            return true;
        }

        return false;
    }

    public static boolean isLenovo208() {
        if ("Lenovo A208t".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isSonyLt18i() {
        if ("LT18i".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isLenovoA788t() {
        if ("Lenovo A788t".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isRedmiNote3() {
        if ("Redmi Note 3".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isOppoR7007() {
        if ("R7007".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isOppoR8007() {
        if ("R8007".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }


    public static boolean isCoolpad5210A() {
        if ("Coolpad 5210A".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    public static boolean isMeizuM351() {
        if ("M351".equals(Build.MODEL)) {
            return true;
        }
        return false;
    }

    /**
     * 索爱LT18i
     * BRAND:SEMC   MODEL:LT18i
     */
    public static boolean isSEMCLT18i() {
        return Build.BRAND.equals("SEMC") && Build.MODEL.equals("LT18i");
    }

    /**
     * K-Touch U81t
     * BRAND:alps   MODEL:K-Touch U81t
     */
    public static boolean isKTouchU81t() {
        return Build.BRAND.equals("alps") && Build.MODEL.equals("K-Touch U81t");
    }

    /**
     * KONKA D6+
     */
    public static boolean isKONKA() {
        return Build.BRAND.equals("KONKA") && Build.MODEL.equals("KONKA D6+");
    }

    /**
     * KONKA D6+
     */
    public static boolean isKOOBEE() {
        return Build.BRAND.equals("koobee") && Build.MODEL.equals("koobee M6");
    }

    /**
     * 360 1503-A01
     * BRAND:360   MODEL:1503-A01
     */
    public static boolean is360N4() {
        return Build.BRAND.equals("360") && Build.MODEL.equals("1503-A01");
    }

    /**
     * Sony S39h
     * BRAND:Sony   MODEL:S39h
     */
    public static boolean isSonyS39h() {
        return Build.BRAND.equals("Sony") && Build.MODEL.equals("S39h");
    }


    /**
     * Lenovo S898t
     * BRAND:Lenovo   MODEL:Lenovo S898t
     */
    public static boolean isLenovoS898t() {
        return Build.BRAND.equals("Lenovo") && Build.MODEL.equals("Lenovo S898t");
    }

    public static boolean isSamsungNote() {
        return Build.BRAND.equals("samsung") && Build.MODEL.startsWith("SM-N");
    }

    public static boolean isSamsung() {
        return Build.BRAND.equals("samsung");
    }

    public static boolean isHuaWeiMate10() {
        return Build.MODEL.equals("BLA-AL00");
    }

    public static boolean isVivoY85A() {
        return Build.MODEL.endsWith("vivo Y85A");
    }

    /**
     * 华为 mate 20
     */
    public static boolean isHMAAL00() {
        return Build.MODEL.equals("HMA-AL00");
    }

    /**
     * 判断是否雪花比较慢的机器
     */
    public static boolean isSnowSlowerDevice() {
        String[] devices = {"HUAWEI D2-0082", "Coolpad8750"};
        String device = Build.MODEL;
        boolean res = false;
        if (!TextUtils.isEmpty(device)) {
            for (String ds : devices) {
                if (device.equals(ds)) {
                    res = true;
                }
            }
        }
        return res;
    }


    /**
     * 判断是否是 View.setAlpha() 异常（显示空白）的机型
     */
    public static boolean isAlphaAbnormalDevice() {
        return TextUtils.equals(Build.MODEL, "ZTE N881E");
    }

    public static int dip2px(Context context, float dpValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        if (context == null) {
            return 0;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        if (context == null) {
            return 0;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 判断手机是否有虚拟按键
     */
    public static boolean isVirtualKeyShow(Context context) {
        if (context == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= 14) {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (resourceId > 0) {
                return resources.getBoolean(resourceId);
            }
        }
        return false;
    }

    // 获取MIUI系统的版本名，如果是MIUI系统，并将版本名存入SharePreference；如果不是则将版本名设置为“NULL”，存入SharePreference。
    public static String getMiuiVersionName(Context context) {
        String miuiVersionName = getSystemProperty(MIUI_VERSION_NAME_PROP_KEY, "NULL");
        if (TextUtils.isEmpty(miuiVersionName)) {
            miuiVersionName = "NULL";
        }

        return miuiVersionName;
    }

    // 获取系统属性配置相关信息 比SharePreference要耗时，相差好几个数量级
    private static String getSystemProperty(String propName, String defaultValue) {
        String line = null;
        BufferedReader input = null;
        InputStreamReader streamReader = null;
        InputStream stream = null;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("getprop " + propName);
            stream = p.getInputStream();
            streamReader = new InputStreamReader(stream);
            input = new BufferedReader(streamReader, 1024);
            line = input.readLine();
        } catch (IOException e) {
            return defaultValue;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
            if (p != null) {
                try {
                    p.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (TextUtils.isEmpty(line)) {
            line = defaultValue;
        }

        return line;
    }

    /**
     * @return 系统版本是否 >= 14 ICE_CREAM_SANDWICH
     */
    public static boolean isAPIHighterThanICE_CREAM_SANDWICH() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static String getSSID(Context context) {
        if (context == null) {
            return "";
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                String ssid = wifiInfo.getSSID();
                if (!TextUtils.isEmpty(ssid)) {
                    ssid = ssid.replaceAll("\"", "");
                }

                return ssid;
            }
        }
        return "";
    }

    /**
     * 返回46000代表移动， 46001代表联调，46003代表电信，默认为中移动46000。
     */
    public static String getOperatorsForOldCodeVersion(Context context) {
        final OperatorType operatorType = getOperators(context);
        return operatorType != null ? operatorType.getCode() : OperatorType.UNKNOWN.getCode();
    }

    /**
     * Return one of CHINA_MOBILE, CHINA_UNICOM, CHINA_TELECOM and UNKNOWN by default.
     */
    public static String getOperatorsAsName(Context context) {
        final OperatorType operatorType = getOperators(context);
        return operatorType != null ? operatorType.name() : OperatorType.UNKNOWN.name();
    }

    /**
     * Return one of 中国移动, 中国联通, 中国电信 and 未知 by default.
     */
    public static String getOperatorsAsChinaName(Context context) {
        final OperatorType operatorType = getOperators(context);
        return operatorType != null ? operatorType.getDescName() : OperatorType.UNKNOWN.getDescName();
    }

    /**
     * (1) 7.2之前的版本中，版本讯飞广告和东方百元接口上传的接口参数，
     * 以46000代表移动， 46001代表联调，46003代表电信，默认值是中移动46000。
     * <p>
     * (2) 7.2版本重新定义返回值，公共参数中上传对应的CHINA_MOBILE，未获取到则默认为UNKNOWN。
     */
    private enum OperatorType {
        CHINA_MOBILE("中国移动", "46000"),
        CHINA_UNICOM("中国联通", "46001"),
        CHINA_TELECOM("中国电信", "46003"),
        UNKNOWN("未知", "46000");

        private final String descName;
        private final String code;

        OperatorType(String descName, String code) {
            this.descName = descName;
            this.code = code;
        }

        public String getDescName() {
            return descName;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 返回运营商 需要权限 <uses-permission android:name="android.permission.READ_PHONE_STATE">
     *
     * @param context
     * @return
     */
    private static OperatorType getOperators(Context context) {
        // 移动设备网络代码（英语：Mobile Network Code，MNC）是与移动设备国家代码（Mobile Country Code，MCC）（也称为“MCC /
        // MNC”）相结合, 例如46000，前三位是MCC，后两位是MNC 获取手机服务商信息

        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                IMSI = tm.getSubscriberId();
            }
        } catch (Exception e) {
            // permission denied.
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(IMSI)) {
            return OperatorType.UNKNOWN;
        }

        // IMSI号前面3位460是国家，紧接着后面2位00 运营商代码
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
            return OperatorType.CHINA_MOBILE;
        } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
            return OperatorType.CHINA_UNICOM;
        } else if (IMSI.startsWith("46003") || IMSI.startsWith("46005")) {
            return OperatorType.CHINA_TELECOM;
        }
        return OperatorType.UNKNOWN;
    }

    public static void clipboard(Context context, String text) {
        if (context == null || TextUtils.isEmpty(text)) {
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.setText(text);
        }
    }

    public static int getTaskSize(Context context) {
        try {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            /** 获取当前正在运行的任务栈列表， 越是靠近当前运行的任务栈会被排在第一位，之后的以此类推 */
            List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
            /** 获得当前最顶端的任务栈，即前台任务栈 */
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            /** 获取前台任务栈的最顶端 Activity */
            return runningTaskInfo.numActivities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }
}
