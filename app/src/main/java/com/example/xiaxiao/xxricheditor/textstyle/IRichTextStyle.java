package com.example.xiaxiao.xxricheditor.textstyle;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/9.
 */

public interface IRichTextStyle {
    public String getStyleName();

    public void executeStyle(RichEditText richEditText);

    public void revokeStyle(RichEditText richEditText);

    public void bind(RichEditText richEditText);

    public void run(boolean status);

    public boolean getStatus();
}
