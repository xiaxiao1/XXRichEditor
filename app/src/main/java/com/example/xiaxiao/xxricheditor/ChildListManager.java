package com.example.xiaxiao.xxricheditor;

import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2018/3/9.
 */

public class ChildListManager {
    ArrayList<ChildElement> childList;


    public ChildListManager() {
        childList = new ArrayList<>();
    }

    public ArrayList<ChildElement> getChildList() {
        return childList;
    }

   /* public void addBefore(ChildElement reference, ChildElement added) {
        if (added==null) {
            return;
        }
        if (childList.size() == 0) {
            childList.add(added);
        } else {
            if (reference == null) {
                ChildElement prior = childList.get(childList.size() - 1);
                prior.setNext(added);
                added.setPrior(prior);
                childList.add(added);
            } else {

                ChildElement prior = reference.getPrior();
                if (prior!=null) {
                    prior.setNext(added);
                }
                added.setPrior(prior);
                added.setNext(reference);

                reference.setPrior(added);

                childList.add(childList.indexOf(reference),added);
            }
        }

    }

    public void addAfter(ChildElement reference, ChildElement added) {
        if (added==null) {
            return;
        }

        if (childList.size() == 0) {
            childList.add(added);
        } else {
            if (reference == null) {
                ChildElement prior = childList.get(childList.size() - 1);
                prior.setNext(added);
                added.setPrior(prior);
                childList.add(added);
            } else {
                ChildElement next = reference.getNext();
                reference.setNext(added);
                added.setPrior(reference);
                added.setNext(next);

                if (next!=null) {
                    next.setPrior(added);
                }
                if (childList.indexOf(reference) + 1 == childList.size()) {
                    childList.add(added);
                } else {
                    childList.add(childList.indexOf(reference),added);
                }
            }
        }
    }

    public ChildElement removeElement(ChildElement element) {
        if (element.getPrior()!=null) {
            ChildElement prior = element.getPrior();
            ChildElement next = element.getNext();
            prior.setNext(next);
            if (next!=null) {
                next.setPrior(prior);
            }
        }
        childList.remove(element);

        return element;
    }*/

    public void addElement(ChildElement reference, ChildElement added, boolean after) {
        if (childList.size() == 0) {
            childList.add(added);
        } else {
            if (reference == null) {
                childList.add(added);
            } else {
                if (after) {
                    if (childList.indexOf(reference) + 1 == childList.size()) {
                        childList.add(added);
                    } else {
                        childList.add(childList.indexOf(reference) + 1, added);
                    }
                } else {
                    childList.add(childList.indexOf(reference),added);
                }
            }
        }
    }

    public void removeElement(ChildElement element) {
        childList.remove(element);
    }

}
