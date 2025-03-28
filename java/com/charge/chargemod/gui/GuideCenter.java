package com.charge.chargemod.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class GuideCenter implements GuidePageDelegate { //多页码索引
    public int nowPage = 0;

    private ArrayList<ChargeBaseScreen> screens = new ArrayList<>();
    public GuideCenter() {
        init();
    }

    public void init() {    //初始化
        addScreen(new ManualScreen());
        addScreen(new LingShiScreen());
    }

    private void addScreen(ChargeBaseScreen screen) {   //方便重复添加的函数
        screen.delegate = this;
        screen.index = screens.size();
        screens.add(screen);
    }

    public void showPage(int page) {    //直接展示页码的函数
        if (page < screens.size() && page >= 0) {
            ChargeBaseScreen willShowScreen = screens.get(page);
            Minecraft.getInstance().setScreen(willShowScreen);
            nowPage = page;
        }
    }

    @Override
    public void changeToPreviousPage(int nowPage) {
        showPage(nowPage - 1);
    }

    @Override
    public void changeToNextPage(int nowPage) {
        showPage(nowPage + 1);
    }
}
