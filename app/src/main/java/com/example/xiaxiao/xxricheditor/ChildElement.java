package com.example.xiaxiao.xxricheditor;

import android.view.View;

/**
 * Created by Administrator on 2018/3/9.
 */

public class ChildElement {
    ChildElement next;
    ChildElement prior;
    public View mView;

    public ChildElement(View view) {
        this.mView = view;
    }

    public ChildElement getNext() {
        return next;
    }

    public void setNext(ChildElement next) {
        this.next = next;
    }

    public ChildElement getPrior() {
        return prior;
    }

    public void setPrior(ChildElement prior) {
        this.prior = prior;
    }
}
