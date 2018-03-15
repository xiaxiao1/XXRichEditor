package com.example.xiaxiao.xxricheditor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.xiaxiao.xxricheditor.textstyle.SizeStyle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditorLayout editorLayout;
    TextView boldView;
    TextView italicView;
    TextView deleteView;
    TextView quoteView;
    TextView sizeView1;
    TextView sizeView2;
    TextView sizeView3;
    TextView sizeView4;
    List<TextView> sizeViews = new ArrayList();
    boolean bold = false;
    boolean italic = false;
    boolean deleteLine = false;
    int textSize = SizeStyle.TEXT_SIZE_NORMAL;
    RichEditorManager richEditorManager;
    private boolean quote = false;
    private StyleController.StyleParams styleParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editorLayout = (EditorLayout) findViewById(R.id.editor_ll);
        richEditorManager = new RichEditorManager(editorLayout);

        boldView = (TextView) findViewById(R.id.boldView);
        italicView = (TextView) findViewById(R.id.italicView);
        deleteView = (TextView) findViewById(R.id.delete_line);
        quoteView = (TextView) findViewById(R.id.quote_tv);
        sizeView1 = (TextView) findViewById(R.id.sizeView1);
        sizeView2 = (TextView) findViewById(R.id.sizeView2);
        sizeView3 = (TextView) findViewById(R.id.sizeView3);
        sizeView4 = (TextView) findViewById(R.id.sizeView4);
        sizeViews.add(sizeView1);
        sizeViews.add(sizeView2);
        sizeViews.add(sizeView3);
        sizeViews.add(sizeView4);


        findViewById(R.id.boldView).setOnClickListener(this);
        findViewById(R.id.italicView).setOnClickListener(this);
        findViewById(R.id.delete_line).setOnClickListener(this);
        findViewById(R.id.quote_tv).setOnClickListener(this);
        findViewById(R.id.sizeView1).setOnClickListener(this);
        findViewById(R.id.sizeView2).setOnClickListener(this);
        findViewById(R.id.sizeView3).setOnClickListener(this);
        findViewById(R.id.sizeView4).setOnClickListener(this);
        editorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editorLayout.edtiorToString();
            }
        });
        editorLayout.setFocusedElementChangedListener(new EditorLayout.FocusedElementChangedListener() {
            @Override
            public void onFocusedChange(ChildElement old, ChildElement now) {
                styleParams = ((RichEditText) now.mView).getRichStyles();
                changeBottonItems(styleParams);
            }
        });
        editorLayout.ready();
        //实时跟新选项item的UI样式还有问题
    }

    private void changeBottonItems(StyleController.StyleParams styleParams) {
        selectView(boldView, styleParams.boldStyle.getStatus());
        selectView(italicView, styleParams.italicStyle.getStatus());
        selectView(deleteView, styleParams.deleteLineStyle.getStatus());
        selectView(quoteView, styleParams.quoteStyle.getStatus());
        int size = styleParams.sizeStyle.getSize();
        selectSizeView(size);

        bold = styleParams.boldStyle.getStatus();
        italic = styleParams.italicStyle.getStatus();
        deleteLine = styleParams.deleteLineStyle.getStatus();
        quote = styleParams.quoteStyle.getStatus();
        textSize = styleParams.sizeStyle.getSize();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.boldView) {
//            bold = styleParams.boldStyle.getStatus();
            bold = !bold;
            selectView((TextView) v, bold);

            richEditorManager.bold().commit();
            selectView((TextView) v, bold);
        }
        if (id == R.id.italicView) {
//            italic = styleParams.italicStyle.getStatus();
            italic = !italic;
            selectView((TextView) v, italic);
            richEditorManager.italic().commit();
        }

        textSize = styleParams.sizeStyle.getSize();
        if (id == R.id.sizeView1) {
            if (textSize == SizeStyle.TEXT_SIZE_SUPER_LARGE) {
                textSize = SizeStyle.TEXT_SIZE_NORMAL;
                selectSizeView(textSize);
            } else {
                textSize = SizeStyle.TEXT_SIZE_SUPER_LARGE;
                selectSizeView(textSize);
            }
            richEditorManager.textSize(textSize).commit();
        }
        if (id == R.id.sizeView2) {
            if (textSize == SizeStyle.TEXT_SIZE_LARGE) {
                textSize = SizeStyle.TEXT_SIZE_NORMAL;
                selectSizeView(textSize);
            } else {
                textSize = SizeStyle.TEXT_SIZE_LARGE;
                selectSizeView(textSize);
            }
            richEditorManager.textSize(textSize).commit();
        }
        if (id == R.id.sizeView3) {
            if (textSize == SizeStyle.TEXT_SIZE_SMALL) {
                textSize = SizeStyle.TEXT_SIZE_NORMAL;
                selectSizeView(textSize);
            } else {
                textSize = SizeStyle.TEXT_SIZE_SMALL;
                selectSizeView(textSize);
            }
            richEditorManager.textSize(textSize).commit();
        }
        if (id == R.id.sizeView4) {
            if (textSize == SizeStyle.TEXT_SIZE_SUPER_SMALL) {
                textSize = SizeStyle.TEXT_SIZE_NORMAL;
                selectSizeView(textSize);
            } else {
                textSize = SizeStyle.TEXT_SIZE_SUPER_SMALL;
                selectSizeView(textSize);
            }
            richEditorManager.textSize(textSize).commit();
        }
        if (id == R.id.delete_line) {
//            deleteLine = styleParams.deleteLineStyle.getStatus();
            deleteLine = !deleteLine;
            selectView((TextView) v, deleteLine);

            richEditorManager.deleteLine().commit();
        }
        if (id == R.id.quote_tv) {
//            quote = styleParams.quoteStyle.getStatus();
            quote = !quote;
            selectView((TextView) v, quote);

            richEditorManager.quote().commit();
        }

    }


    public void selectView(TextView textView, boolean select) {


        if (select) {
            textView.setTextColor(Color.RED);
            textView.setBackgroundResource(R.drawable.item_bg);
        } else {
            textView.setTextColor(Color.BLUE);
            textView.setBackgroundResource(0);
        }
    }

    public void selectSizeView(int size) {
        /*for (TextView t : sizeViews) {
            t.setTextColor(Color.BLUE);
            t.setBackgroundResource(0);
        }
        if (select) {
            for (TextView tt : sizeViews) {
                if (tt == textView) {
                    tt.setTextColor(Color.RED);
                    tt.setBackgroundResource(R.drawable.item_bg);
                }
            }
        }*/
        if (size == SizeStyle.TEXT_SIZE_SUPER_LARGE) {
            sizeViews.get(0).setTextColor(Color.RED);
            sizeViews.get(0).setBackgroundResource(R.drawable.item_bg);
        } else {
            sizeViews.get(0).setTextColor(Color.BLUE);
            sizeViews.get(0).setBackgroundResource(0);
        }

        if (size == SizeStyle.TEXT_SIZE_LARGE) {
            sizeViews.get(1).setTextColor(Color.RED);
            sizeViews.get(1).setBackgroundResource(R.drawable.item_bg);
        } else {
            sizeViews.get(1).setTextColor(Color.BLUE);
            sizeViews.get(1).setBackgroundResource(0);
        }

        if (size == SizeStyle.TEXT_SIZE_SMALL) {
            sizeViews.get(2).setTextColor(Color.RED);
            sizeViews.get(2).setBackgroundResource(R.drawable.item_bg);
        } else {
            sizeViews.get(2).setTextColor(Color.BLUE);
            sizeViews.get(2).setBackgroundResource(0);
        }

        if (size == SizeStyle.TEXT_SIZE_SUPER_SMALL) {
            sizeViews.get(3).setTextColor(Color.RED);
            sizeViews.get(3).setBackgroundResource(R.drawable.item_bg);
        } else {
            sizeViews.get(3).setTextColor(Color.BLUE);
            sizeViews.get(3).setBackgroundResource(0);
        }
    }
}
