package com.example.xiaxiao.xxricheditor.textstyle;

import android.graphics.Typeface;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/10.
 */

public class BoldItalicStyle extends AbsRichTextStyle {
    public  String name = "bold&italic-加粗斜体";

    @Override
    public void executeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.BOLD_ITALIC);
    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.NORMAL);
    }

}
