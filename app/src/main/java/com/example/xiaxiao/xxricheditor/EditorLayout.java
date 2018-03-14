package com.example.xiaxiao.xxricheditor;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiaxiao.xxricheditor.textstyle.IRichTextStyle;
import com.example.xiaxiao.xxricheditor.textstyle.SizeStyle;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class EditorLayout extends LinearLayout {
    private Context mContext;
    static String TAG = "EditorLayout";

    public EditorLayout(Context context) {
        super(context);
        init(context);
    }

    public EditorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    ChildListManager childListManager;
    ChildElement currentElement;

    private void init(Context c) {
        this.mContext = c;
        childListManager = new ChildListManager();
//        createANewElement();

    }

    /**
     * 添加一个view
     * @param view
     */
    public void addElementView(View view) {

        if (childListManager.getChildList().indexOf(currentElement) == childListManager.getChildList().size() - 1) {
            //是最后一个
            this.addView(view);
        } else {
            this.addView(view, childListManager.getChildList().indexOf(currentElement)+1);
        }

        ChildElement childElementAdded = new ChildElement(view);
        childListManager.addElement(currentElement, childElementAdded,true);

        //选中当前新添加的view
//        currentElement = childElementAdded;
        ((ChildViewInterface)view).setParentEditor(this);
        Util.log(TAG,"添加了一个新的view");

        view.requestFocus();

//        edtiorToString();
    }

    public void removeElementView(View view) {
        if (view == currentElement.mView) {

            if (getChildCount()==1) {
                //最后一个不允许删除
                return;
            }

            ChildElement temp=null;
            List<ChildElement> childElements = childListManager.getChildList();
           int i1=childElements.indexOf(currentElement) - 1;
            if (i1<0) {
                i1 = childElements.indexOf(currentElement) + 1;
            }
            if (i1 >= 0 && i1 < childListManager.getChildList().size()) {
                temp= childListManager.getChildList().get(i1);
            }
            childListManager.removeElement(currentElement);
            this.removeView(view);
            currentElement=temp;
            if (currentElement!=null) {
                currentElement.mView.requestFocus();
//                ((RichEditText)currentElement.mView).setSelection();
            }

            Util.log(TAG, "删除了当前view");
        } else {
            Util.log(TAG,"要删除的view和当前currentelement不符合，无法删除");
        }
    }


    public void createANewElement() {
        RichEditText r = new RichEditText(mContext);
        Util.log(TAG,"创建了一个新的view");
        giveDefaultLayoutParams(r);
        this.addElementView(r);
    }

    private void giveDefaultLayoutParams(RichEditText r) {
        r.setBackground(null);
        r.setTextSize(SizeStyle.TEXT_SIZE_NORMAL);
        Util.log(TAG,"为新view添加默认的参数");
    }

    public void setCurrentElement(View view) {
        for (ChildElement childElement : childListManager.getChildList()) {
            if (childElement.mView==view) {
                if (this.focusedElementChangedListener!=null) {
                    focusedElementChangedListener.onFocusedChange(this.currentElement,childElement);
                }
                this.currentElement = childElement;
                Util.log(TAG,"设置当前的currentelement View");
                break;
            }
        }
    }

    public void edtiorToString() {
        StringBuffer stringBuffer = new StringBuffer();
        List<ChildElement> list = childListManager.getChildList();
        RichEditText richTextView;
        for (ChildElement childElement : list) {
            richTextView = (RichEditText) childElement.mView;
            stringBuffer.append(richTextView.getText().toString());
            stringBuffer.append("\n");
        }
        Util.log(TAG,stringBuffer.toString());
    }

    private FocusedElementChangedListener focusedElementChangedListener;

    public void setFocusedElementChangedListener(FocusedElementChangedListener focusedElementChangedListener) {
        this.focusedElementChangedListener = focusedElementChangedListener;
    }

    /*
    * 当一切准备工作就绪后调用此方法，表示editorlayout已经初始化各数据和参数完毕，可以响应用户的标记操作了
    *
    * 唉 其实就是为了解决在init中初始化第一个item的时候 focuschangelistener无法监听到，因为那时候监听器还没有初始化呢
    * */
    public void ready() {
        createANewElement();
    }

    public interface FocusedElementChangedListener{
        public void onFocusedChange(ChildElement old, ChildElement now);
    }
}
