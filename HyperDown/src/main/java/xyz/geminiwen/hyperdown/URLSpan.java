package xyz.geminiwen.hyperdown;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;

/**
 * Created by geminiwen on 18/10/2016.
 */

public class URLSpan extends android.text.style.URLSpan {
    public URLSpan(String url) {
        super(url);
    }

    public URLSpan(Parcel in) {
        super(in);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#009A61"));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }



    public static final Creator<URLSpan> CREATOR = new Creator<URLSpan>() {
        @Override
        public URLSpan createFromParcel(Parcel source) {
            return new URLSpan(source);
        }

        @Override
        public URLSpan[] newArray(int size) {
            return new URLSpan[size];
        }
    };
}
