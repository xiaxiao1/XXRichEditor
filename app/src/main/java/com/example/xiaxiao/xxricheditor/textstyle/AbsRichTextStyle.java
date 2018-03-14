package com.example.xiaxiao.xxricheditor.textstyle;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/14.
 */

public abstract class AbsRichTextStyle implements IRichTextStyle {
    public  String name;
    private boolean status=false;
    RichEditText richEditText;

    public AbsRichTextStyle() {
    }

    @Override
    public void bind(RichEditText richEditText) {
        this.richEditText = richEditText;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public String getStyleName() {
        return name;
    }

    @Override
    public void run(boolean status) {
        this.status = status;
        if (status) {
            executeStyle(richEditText);
        } else {
            revokeStyle(richEditText);
        }
    }
}
