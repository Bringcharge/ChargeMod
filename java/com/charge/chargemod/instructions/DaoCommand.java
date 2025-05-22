package com.charge.chargemod.instructions;

import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

public class DaoCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dao").then(Commands.argument("Instruction", StringArgumentType.string()).executes((context) -> {
                makeInstruction(context.getSource(), StringArgumentType.getString(context, "Instruction"));
                return 1;
        }))
        );
    }

    private static void makeInstruction(CommandSourceStack source, String str) {
        Instruction ins = new Instruction();
//        ins.str = "F002_V103_V101_DI4_{Fif_B001_E201_{F004_E101_E201_}{F003_E101_V201_}}";   // 大概是一个射箭然后射中了会触发if判断的东西
//        ins.str = "F004_E101_E301_V103_V101_E101_"; //视野看到的单位，交换位置
        ins.str = str;
        InstructionsModel model = new InstructionsModel();
        model.user = source.getPlayer();
        model.holder = ItemStack.EMPTY;
        InstructionsManager.functionWithString(ins, model);    //命令字符串
        source.sendSuccess(() -> Component.translatable("释放道法"), true);
    }

}