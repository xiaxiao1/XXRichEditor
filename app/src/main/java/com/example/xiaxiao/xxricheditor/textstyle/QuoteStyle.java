package com.example.xiaxiao.xxricheditor.textstyle;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/10.
 */

public class QuoteStyle extends AbsRichTextStyle {
    public  String name = "引用-quote";
    private BackgroundColorSpan backgroundColorSpan;
    private Editable editable;


    @Override
    public void executeStyle(RichEditText richEditText) {
        if (backgroundColorSpan==null) {
            backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#eeeeee"));
        }
        editable = richEditText.getText();
        editable.setSpan(backgroundColorSpan,0, editable.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
        editable = richEditText.getText();
        editable.removeSpan(backgroundColorSpan);
    }

}
