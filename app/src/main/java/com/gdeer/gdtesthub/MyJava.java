package com.gdeer.gdtesthub;

public class MyJava {
    public static void main(String[] args) {
        String cn = "你";
        System.out.println(cnToUnicode(cn));
        // 字符串 : \u5f00\u59cb\u4efb\u52a1 ，由于 \ 在java里是转义字符，要写出下面这种形式
        String unicode = "\\u5f00\\u59cb\\u4efb\\u52a1";
        System.out.println(unicodeToCn(unicode));
    }

    private static String unicodeToCn(String unicode) {
        // 有两种转义，一个是 java 字符串中的转义，一个是 regex 中的转义
        // "\\u" 字面值，java 字符串转义为 "\\\\u"，regex 转义为 "\\\\\\\\u"
        // "\ u" 字面值，java 字符串转义为 "\\u"，regex 转义为 "\\\\u"
        // 上一行中的 "\ u"，加一个空格是因为不加的话，在 java doc 的编译过程中会识别 unicode，报非法的 Unicode 转义
        String[] strs;
        if (unicode.contains("\\\\u")) {
            strs = unicode.split("\\\\\\\\u");
        } else {
            strs = unicode.split("\\\\u");
        }
        StringBuilder returnStr = new StringBuilder();
        // 由于unicode字符串以 "\\u" 或 "\ u" 开头，因此分割出的第一个字符是""。
        try {
            for (int i = 1; i < strs.length; i++) {
                returnStr.append((char) Integer.valueOf(strs[i], 16)
                    .intValue());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return returnStr.toString();
    }

    private static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
}
