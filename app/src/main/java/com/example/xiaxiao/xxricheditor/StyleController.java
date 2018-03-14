package com.example.xiaxiao.xxricheditor;

import com.example.xiaxiao.xxricheditor.textstyle.BoldItalicStyle;
import com.example.xiaxiao.xxricheditor.textstyle.BoldStyle;
import com.example.xiaxiao.xxricheditor.textstyle.DeleteLineStyle;
import com.example.xiaxiao.xxricheditor.textstyle.ItalicStyle;
import com.example.xiaxiao.xxricheditor.textstyle.QuoteStyle;
import com.example.xiaxiao.xxricheditor.textstyle.SizeStyle;

/**
 * Created by Administrator on 2018/3/10.
 */

public class StyleController {
    boolean isFresh=false;
    private RichEditText richEditText;

    public boolean bold = false;
    public boolean italic = false;
    public boolean deleteLine=false;
    public boolean quote=false;
    public int textSize = SizeStyle.TEXT_SIZE_NORMAL;

    private BoldStyle           boldStyle;
    private ItalicStyle         italicStyle;
    private BoldItalicStyle     boldItalicStyle;
    private SizeStyle           sizeStyle;
    private DeleteLineStyle     deleteLineStyle;
    private QuoteStyle          quoteStyle;

    public StyleController(RichEditText richEditText) {
        this.richEditText = richEditText;
        boldStyle = new BoldStyle();
        italicStyle = new ItalicStyle();
        boldItalicStyle = new BoldItalicStyle();
        sizeStyle = new SizeStyle(SizeStyle.TEXT_SIZE_NORMAL);
        deleteLineStyle = new DeleteLineStyle();
        quoteStyle = new QuoteStyle();

        boldStyle.bind(richEditText);
        italicStyle.bind(richEditText);
        boldItalicStyle.bind(richEditText);
        sizeStyle.bind(richEditText);
        deleteLineStyle.bind(richEditText);
        quoteStyle.bind(richEditText);

    }
    public void commit() {
      /*  if (!isFresh) {
            throw new Exception("the controller is not fresh, please get by RichEditormanager#getStyleController()");
        }*/

/*
        if (deleteLine) {
            deleteLineStyle.executeStyle(richEditText);
        } else {
            deleteLineStyle.revokeStyle(richEditText);
        }

        boldItalicStyle.revokeStyle(richEditText);

        if (bold && italic) {
            boldItalicStyle.executeStyle(richEditText);
        } else {
            if (bold) {
                boldStyle.executeStyle(richEditText);
            } else if (italic) {
                italicStyle.executeStyle(richEditText);
            }
        }

        if (quote) {
            quoteStyle.executeStyle(richEditText);
        } else {
            quoteStyle.revokeStyle(richEditText);
        }
        */

        deleteLineStyle.run(deleteLine);

        boldItalicStyle.revokeStyle(richEditText);

        if (bold && italic) {
            boldItalicStyle.run(bold);
        } else {
            if (bold) {
                boldStyle.run(bold);
            } else if (italic) {
                italicStyle.run(italic);
            }
        }

       quoteStyle.run(quote);

        sizeStyle.setSize(textSize);
        sizeStyle.executeStyle(richEditText);
    }

    public StyleParams getStyles() {
        StyleParams styleParams = new StyleParams();
        styleParams.boldStyle = this.boldStyle;
        styleParams.italicStyle=   this.italicStyle;
        styleParams.boldItalicStyle=this.boldItalicStyle;
        styleParams.sizeStyle=      this.sizeStyle;
        styleParams.deleteLineStyle=this.deleteLineStyle;
        styleParams.quoteStyle=     this.quoteStyle;

        return styleParams;
    }

    public class StyleParams{
        public BoldStyle            boldStyle;
        public ItalicStyle          italicStyle;
        public BoldItalicStyle      boldItalicStyle;
        public SizeStyle            sizeStyle;
        public DeleteLineStyle      deleteLineStyle;
        public QuoteStyle           quoteStyle;
    }
}
