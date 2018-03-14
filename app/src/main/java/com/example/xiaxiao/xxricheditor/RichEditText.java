package com.example.xiaxiao.xxricheditor;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaxiao.xxricheditor.textstyle.IRichTextStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class RichEditText extends EditText implements ChildViewInterface {

    EditorLayout parentEditor;
    static String TAG = "RichEditText";
    private boolean canBeDeleted = false;

    List<IRichTextStyle> textStyleList;
    StyleController styleController;


    public RichEditText(Context context) {
        super(context);
        init();
    }

    public RichEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (styleController!=null) {
            drawDeleteLine(styleController.deleteLine,canvas);
        }
    }

    private void drawDeleteLine(boolean deleteLine, Canvas canvas) {

    }

    public List<IRichTextStyle> getTextStyleList() {
        return textStyleList;
    }

    public void setParentEditor(EditorLayout parentEditor) {
        this.parentEditor = parentEditor;
    }

    @Override
    public EditorLayout getParentEditor() {
        return this.parentEditor;
    }

    public StyleController getStyleController() {
        return styleController;
    }

    public void init() {
        textStyleList = new ArrayList<>();
        styleController = new StyleController(this);
        this.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int keyCode = event.getKeyCode();
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Util.log(TAG, "点击了 确定 按钮");
                    if (RichEditText.this.getSelectionEnd()== RichEditText.this.getText().length()) {
                        requestCreateNewElement();
                        return true;
                    }
                }
                return false;
            }
        });
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                int keyCode = event.getKeyCode();
                Util.log(TAG, "正在接收按键");


//                canBeDeleted=false;

                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_UP) {
                    if (canBeDeleted) {
                        requestDeleteMe();
                    }
                    Util.log(TAG, "点击了删除按钮 length=" + RichEditText.this.getText().length());

                }
                if (RichEditText.this.getText().toString().length() == 0) {
                    //因为这个监听总是在edittext已经删除完毕时才执行的，无法在这里提前操作，所以用这种方式代替
                    canBeDeleted = true;
                } else {
                    canBeDeleted = false;
                }
                return false;
            }
        });

        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == RichEditText.this) {
                    if (hasFocus) {
                        Util.log(TAG, "获得了焦点");
                        requestToBeCurrentElement();
                    }
                }
            }
        });

    }

    /**
     * 时刻更新编辑器保存的当前的currentElement
     */
    private void requestToBeCurrentElement() {
        Util.log(TAG, "以获取焦点 ，请求成为currentElement view");
        parentEditor.setCurrentElement(this);
    }

    /**
     * 请求父view创建新的一个view元素，换行了嘛
     */
    private void requestCreateNewElement() {
        Util.log(TAG, "点击了 确定 按钮，请求创建一个新 view");
        if (parentEditor != null) {
            parentEditor.createANewElement();
        }
    }

    /**
     * 请求父view删除当前view item
     */
    private void requestDeleteMe() {
        Util.log(TAG, "点击了删除按钮,空内容了，请求删除我");
        if (parentEditor != null) {
            parentEditor.removeElementView(this);
        }
    }


    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);
    }

    public StyleController.StyleParams getRichStyles() {
        return this.getStyleController().getStyles();
    }
}
