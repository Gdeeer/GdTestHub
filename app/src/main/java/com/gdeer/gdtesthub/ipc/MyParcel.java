package com.gdeer.gdtesthub.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable 接口可以存入在 Parcel 中，从而在 Binder 中传输
 * 实现复杂，但效率高。适合在内存中使用
 */
public class MyParcel implements Parcelable {

    int id;
    String content;

    protected MyParcel(Parcel in) {
        id = in.readInt();
        content = in.readString();
    }

    public static final Creator<MyParcel> CREATOR = new Creator<MyParcel>() {
        @Override
        public MyParcel createFromParcel(Parcel in) {
            return new MyParcel(in);
        }

        @Override
        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(1);
        dest.writeString("hello");
    }
}
