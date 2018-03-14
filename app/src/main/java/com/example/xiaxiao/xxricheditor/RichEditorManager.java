package com.example.xiaxiao.xxricheditor;

/**
 * Created by Administrator on 2018/3/10.
 */

public class RichEditorManager {
    String TAG = "RichEditorManager";
    EditorLayout editorLayout;

    public RichEditorManager(EditorLayout editorLayout) {
        this.editorLayout = editorLayout;
    }
    private StyleController getStyleController() {
        return  ((RichEditText) (editorLayout.currentElement.mView)).getStyleController();
    }

    public RichEditorManager bold(boolean bold) {
        getStyleController().bold = bold;
        return this;
    }

    public RichEditorManager italic(boolean italic) {
        getStyleController().italic = italic;
        return this;
    }

    public RichEditorManager deleteLine(boolean deleteLine) {
        getStyleController().deleteLine = deleteLine;
        return this;
    }

    public RichEditorManager quote(boolean quote) {
        getStyleController().quote = quote;
        return this;
    }

    public RichEditorManager textSize(int textSize) {
        getStyleController().textSize = textSize;
        return this;
    }


    /*
    * 和UI配合的
    * */
    public RichEditorManager bold() {
        boolean bold = !getStyleController().bold;
        getStyleController().bold = bold;
        return this;
    }

    public RichEditorManager italic() {
        boolean italic = !getStyleController().italic;
        getStyleController().italic = italic;
        return this;
    }

    public RichEditorManager deleteLine() {
        boolean deleteLine = !getStyleController().deleteLine;
        getStyleController().deleteLine = deleteLine;
        return this;
    }
    public RichEditorManager quote() {
        boolean quote = !getStyleController().quote;
        getStyleController().quote = quote;
        return this;
    }

    public void getRichContent() {
     Util.log(TAG,"获取出编辑器上的富文本内容，用来保存，发送等");
    }

    public void commit() {
        getStyleController().commit();

    }



}
