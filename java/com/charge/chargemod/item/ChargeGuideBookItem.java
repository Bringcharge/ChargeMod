package com.charge.chargemod.item;

import com.charge.chargemod.gui.GuideCenter;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//天书，说明书
public class ChargeGuideBookItem extends Item {


    public ChargeGuideBookItem() {
        super(new Item.Properties().stacksTo(1));
    }

    GuideCenter guideCenter;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {   //展示教程书就行
        if (level.isClientSide) {
            if (guideCenter == null) {
                guideCenter = new GuideCenter();
            }
            guideCenter.showPage(guideCenter.nowPage);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
