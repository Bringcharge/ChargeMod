package com.charge.chargemod.gui;

import com.charge.chargemod.gui.pellet.*;
import com.charge.chargemod.gui.swords.*;
import com.charge.chargemod.gui.talisman.*;
import com.charge.chargemod.item.sword.SuGuoSword;
import net.minecraft.client.Minecraft;

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
        addScreen(new LevelScreen());
        //丹药
        addScreen(new BiGuPelletScreen());//辟谷丹
        addScreen(new HongYunPelletScreen());//鸿运齐天丹
        addScreen(new HealthPelletScreen());//大还丹
        addScreen(new PowerPelletScreen());//生力丹
        addScreen(new PoWanFaPelletScreen());//破万法丹
        addScreen(new JuLingPelletScreen());//聚灵丹
        addScreen(new WanWuShengPelletScreen());//万物生丹
        addScreen(new SanShiPelletScreen());//三尸丹
        addScreen(new DengXianPelletScreen());//登仙丹
        //符
        addScreen(new PushBackTalismanScreen());//退魔符
        addScreen(new ShieldTalismanScreen());//御劫符
        addScreen(new SkullStealTalismanScreen());//换颅符
        addScreen(new HoldLifeTalismanScreen());//延死符
        addScreen(new ReliveTalismanScreen());//回生符
        addScreen(new FlyTalismanScreen());//仙游符
        addScreen(new RainTalismanScreen());//祈雨符
        addScreen(new InviteGodTalismanScreen());//请神符
        addScreen(new QiuBuDeTalismanScreen());//仙符 求不得
        addScreen(new JieBuChuTalismanScreen());//仙符 解不出
        addScreen(new XiangBuTongTalismanScreen());//仙符 想不通
        addScreen(new SuanBuDuiTalismanScreen());//仙符 算不对
        addScreen(new MazeTalismanScreen());//仙符 迷惘
        //剑
        addScreen(new KongFangScreen());//金剑——孔方
        addScreen(new FuYaoScreen());//木剑——扶摇
        addScreen(new WaterSplitScreen());//水剑——断水
        addScreen(new EmberScreen());//火剑——余烬
        addScreen(new ZhongZhongGuScreen());//土剑——冢中骨
        addScreen(new XunYinScreen());//普通剑——寻因
        addScreen(new FengWanLiScreen());//金剑高级——藏锋万里
        addScreen(new YuFengYuScreen());//木剑高级——欲风雨
        addScreen(new BaiCaoLingScreen());//水剑高级——百草零
        addScreen(new YuanTianHanScreen());//火剑高级——愿天寒
        addScreen(new EarthBeatScreen());//土剑高级—地动
        addScreen(new RealSwordScreen());//高级剑——真
        addScreen(new FakeSwordScreen());//高级剑——假
        addScreen(new SuGuoScreen());//高级剑——塑果
        addScreen(new WuXiangScreen());//仙剑——无相
        addScreen(new QiuXiaoYaoScreen());//仙剑——求逍遥
        addScreen(new LuanYinGuoScreen());//仙剑——乱因果
        //阵
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
