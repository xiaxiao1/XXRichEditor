package com.example.xiaxiao.xxricheditor.textstyle;

import android.graphics.Typeface;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/9.
 */

public class BoldStyle extends AbsRichTextStyle {
    public  String name = "加粗-bold";

    @Override
    public void executeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.BOLD);
    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
        richEditText.setTypeface(null, Typeface.NORMAL);
    }

}
