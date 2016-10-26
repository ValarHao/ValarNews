package com.valarhao.valarnews.module.zhihu.common;

public abstract class RecyclerItem {

    private boolean mReadState;

    public abstract String getImgLink();
    public abstract String getTitle();
    public abstract int getId();

    public void setReadState(boolean readState) {
        mReadState = readState;
    }

    public boolean getReadState() {
        return mReadState;
    }
}
