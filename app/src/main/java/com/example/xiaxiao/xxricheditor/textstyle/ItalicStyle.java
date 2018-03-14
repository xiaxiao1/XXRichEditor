package com.example.xiaxiao.xxricheditor.textstyle;

import android.graphics.Typeface;

import com.example.xiaxiao.xxricheditor.RichEditText;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class ItalicStyle extends AbsRichTextStyle {
    public String name = "斜体-italic";

    @Override
    public void executeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.ITALIC);
    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.NORMAL);
    }
}