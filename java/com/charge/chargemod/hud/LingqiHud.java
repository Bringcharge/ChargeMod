package com.charge.chargemod.hud;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.awt.*;

public class LingqiHud implements IGuiOverlay {
    // 定义一个静态常量 hud，用于存储 ExampleHud 的单例对象
    private static final LingqiHud hud = new LingqiHud();
    // 定义一个 ResourceLocation 对象，用于存储 HUD 纹理的路径
//    private final ResourceLocation HUD = new ResourceLocation(ChargeModItemRegistry.MODID, "textures/gui/hud.png");

    public LingqiHud() {
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        // 检查玩家主手持有的物品是否为 xx，如果不是则直接返回
//        if (Minecraft.getInstance().player.getMainHandItem().getItem()!= ChargeModItemRegistry.chargeBaseIngot.get())
//            return;

        Player player = Minecraft.getInstance().player; //获取玩家
        int lingqi = PlayerLingQiHelper.getLingQi(player);
        int maxLingQi = PlayerLingQiHelper.getMaxLingQi(player);
        if (maxLingQi == 0) {   //没有就不渲染了
            return;
        }
        int width = 10 ; // 魔法槽的总宽度
        int height = 200; // 魔法槽的高度
        int x = 10; // 魔法槽的左上角 X 坐标
        int y =  screenHeight / 2 - 100; // 魔法槽的左上角 Y 坐标


        // 绘制外部白色边框
        guiGraphics.fill(x - 1, y - 1, x + width + 1, y + height + 1, 0xFFFFFFFF); // 白色边框
        guiGraphics.fill(x, y, x + width, y + height, 0xFFAAAAAA); // 灰色背景
        int lockHeight = (int) ((300 - maxLingQi) / 300.0 * (height));
        guiGraphics.fill(x, y, x + width, y + lockHeight, 0xFF444444); // 封锁区域

        // 计算内部绿色填充的宽度
        int fillHeight = (int) (lingqi / 300.0 * height);    //除以最大灵气，乘以当前宽度
        String str = String.valueOf(lingqi);
        // 绘制内部绿色填充
        //x,y起点 x,y终点 argb颜色0xaarrggbb
        guiGraphics.fill(x , y - fillHeight + height , x + width , y + height, 0xFF019C26); // 绿色填充
        guiGraphics.drawString(Minecraft.getInstance().font, str, x - 2, y + 2 + height, 0xFF019C26);   //文字
    }
    // 提供一个静态方法用于获取 ExampleHud 的单例对象
    public static LingqiHud getInstance() {
        return hud;
    }
}
