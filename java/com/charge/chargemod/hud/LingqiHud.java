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

        int width = 200 ; // 魔法槽的总宽度
        int height = 10; // 魔法槽的高度
        int x = (int) ((screenWidth - width) / 2.0); // 魔法槽的左上角 X 坐标
        int y = screenHeight - 50; // 魔法槽的左上角 Y 坐标


        // 绘制外部白色边框
        guiGraphics.fill(x, y, x + width, y + height, 0xFFFFFFFF); // 白色边框
        guiGraphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, 0xFFAAAAAA); // 白色边框


        // 计算内部绿色填充的宽度
        int fillWidth = (int) (lingqi / 200.0 * width);    //除以最大灵气，乘以当前宽度
        String str = String.valueOf(lingqi);
        // 绘制内部绿色填充
        //x,y起点 x,y终点 argb颜色0xaarrggbb
        guiGraphics.fill(x + 1, y + 1, x + fillWidth, y + height - 1, 0xFF00FF00); // 绿色填充
        guiGraphics.drawString(Minecraft.getInstance().font, str, x - 10, y, 0xFF00FF00);
    }
    // 提供一个静态方法用于获取 ExampleHud 的单例对象
    public static LingqiHud getInstance() {
        return hud;
    }
}
