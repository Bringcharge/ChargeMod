package com.charge.chargemod.gui;

public interface GuidePageDelegate {
    public void changeToPreviousPage(int nowPage);
    public void changeToNextPage(int nowPage);
    public void changeToPageWithIndex(int index);
}
