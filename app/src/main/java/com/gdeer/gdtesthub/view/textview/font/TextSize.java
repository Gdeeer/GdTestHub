package com.gdeer.gdtesthub.view.textview.font;

/**
 * author : chenzhenbing
 * date : 2020/4/27 22:12
 * desc : 以中文字号命名枚举
 */
public enum TextSize {

    DEFAULT_34(1, 40, 34, ""),
    DEFAULT_26(2, 30, 26, ""),
    DEFAULT_24(3, 26, 24, ""),
    DEFAULT_22(4, 24, 22, ""),
    DEFAULT_20(5, 22, 20, ""),
    DEFAULT_18(6, 20, 18, ""),
    DEFAULT_16(7, 18, 16, ""),
    DEFAULT_14(8, 15, 14, ""),
    DEFAULT_12(9, 13, 12, ""),
    DEFAULT_10(10, 11, 10, ""),
    DEFAULT_9(11, 9, 9, ""),
    DEFAULT_8(12, 8, 8, ""),

    DIN_24(101, 30, 24, "din.ttf"),
    DIN_16(102, 20, 16, "din.ttf"),
    DIN_14(103, 18, 14, "din.ttf"),

    HELVETICA_44(201, 55, 44, "hel.ttf"),
    HELVETICA_20(202, 23, 20, "hel.ttf");

    private int id;

    private int chSize;

    private int enSize;

    private String textFont;

    TextSize(int id, int enSize, int chSize, String textFont) {
        this.id = id;
        this.chSize = chSize;
        this.enSize = enSize;
        this.textFont = textFont;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public int getChSize() {
        return chSize;
    }

    public void setChSize(int chSize) {
        this.chSize = chSize;
    }

    public int getEnSize() {
        return enSize;
    }

    public void setEnSize(int enSize) {
        this.enSize = enSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static TextSize getSizeFromId(int id) {
        if (id > 0) {
            for (TextSize size : TextSize.values()) {
                if (id == (size.getId())) {
                    return size;
                }
            }
        }
        return null;
    }
}
