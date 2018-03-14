package com.example.xiaxiao.xxricheditor.textstyle;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/9.
 */

public class SizeStyle extends AbsRichTextStyle {
    public final static int TEXT_SIZE_SUPER_SMALL=10;

    public final static int TEXT_SIZE_SMALL=15;

    public final static int TEXT_SIZE_NORMAL=20;

    public final static int TEXT_SIZE_LARGE=25;

    public final static int TEXT_SIZE_SUPER_LARGE=35;


    public String name = "大小-size";
    int size=20;

    public SizeStyle(int size) {
        if (size<TEXT_SIZE_SMALL||size>TEXT_SIZE_SUPER_LARGE) {
            size = TEXT_SIZE_NORMAL;
        }
        this.size = size;

    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void executeStyle(RichEditText richEditText) {
        richEditText.setTextSize(size);
    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
        size = TEXT_SIZE_NORMAL;
        richEditText.setTextSize(size);
    }

}
