package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

//其实这玩意叫愿天寒
public class MayColdSword extends ChargeBaseSword {
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);

        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.consume(itemstack);
    }
    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 20 * 3;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int p_41431_) {
        super.onUseTick(level, user, stack, p_41431_);

        if (!level.isClientSide && user instanceof Player) {
            Player player = (Player)user;
            boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 1);
            if (!canUse) {
                player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
            }
        }
        //构建扫视范围
        float halfWidth = 7;
        float halfHeight = 7;
        Vec3 eyePosition = user.getEyePosition(1.0F);
        Vec3 topNorthEast = user.getEyePosition().add(halfWidth, halfHeight, halfWidth);
        Vec3 bottomNorthWest = user.getEyePosition().add(-halfWidth, -halfHeight, -halfWidth);
        Vec3 lookVec = user.getLookAngle();
        AABB aabb = new AABB(topNorthEast, bottomNorthWest); //扫视的范围

        List<Entity> list = user.level().getEntities(user, aabb, entity -> {    //大概是找个大范围，然后从里面取了一个圆锥。用过滤函数处理的
            Vec3 position = entity.getPosition(1.0f);
            Vec3 toEntity = position.add(eyePosition.reverse());
            double dis = toEntity.length();

            double angel = Math.acos(toEntity.dot(lookVec) / (dis * lookVec.length()));
            boolean flag = angel < (Math.PI / 4.0);

            return (entity instanceof LivingEntity && entity != user && flag);
        });   //找寻范围内的单位，排除自己
        if (list.size() > 0) {  //有单位可以处理，后续增加扣蓝代码和
            if (!user.level().isClientSide) {   //判断服务端
                for (Entity entity : list) {
                    for (int i = 0; i < 3; i++) {
                        DamageSource damageSource = DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_REAL);
                        entity.hurt(damageSource, 2);
                    }
                }
            }
        }
    }

}
