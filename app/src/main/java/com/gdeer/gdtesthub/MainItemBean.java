package com.gdeer.gdtesthub;

class MainItemBean {
    private String desc;
    private Class clazz;

    public MainItemBean(String desc, Class clazz) {
        this.desc = desc;
        this.clazz = clazz;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
