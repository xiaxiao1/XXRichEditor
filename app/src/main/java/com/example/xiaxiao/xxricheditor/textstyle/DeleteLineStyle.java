package com.example.xiaxiao.xxricheditor.textstyle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.example.xiaxiao.xxricheditor.RichEditText;

/**
 * Created by Administrator on 2018/3/10.
 */

public class DeleteLineStyle extends AbsRichTextStyle {
    public  String name = "删除线";
    private SpannableString spannableString;
    private StrikethroughSpan strikethroughSpan = new StrikethroughSpan();;


    @Override
    public void executeStyle(RichEditText richEditText) {
        spannableString = new SpannableString(richEditText.getText());
        spannableString.setSpan(strikethroughSpan,0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        richEditText.setText(spannableString);
        richEditText.setSelection(spannableString.length());

    }

    @Override
    public void revokeStyle(RichEditText richEditText) {
         richEditText.getText().removeSpan(strikethroughSpan);

    }

}
