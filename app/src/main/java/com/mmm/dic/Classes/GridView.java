package com.mmm.dic.Classes;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class GridView extends GridLayoutManager {

    public GridView(Context context, int spanCount) {
        super(context, spanCount);
    }
    @Override
    protected boolean isLayoutRTL(){
        return true;
    }
}
