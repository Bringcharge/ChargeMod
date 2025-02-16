package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.doub.DoubleManager;

public class DoubleInstruction {
    public static double parser(Instruction instruction, InstructionsModel owner) {    //����

        String order = null;
        for (int i = 0; i<instruction.str.length(); i++) {
            if (instruction.str.charAt(i) == '#') {
                order = instruction.str.substring(0, i+1);    //�ó�����
                if (i < instruction.str.length()) {
                    instruction.str = instruction.str.substring(i + 1); //�����µ�ֵ
                }
                break;
            }
        }

        if (order != null && order.substring(0,2).equals("DI")) {   //DIֱ��ȡ����
            String dou = order.substring(2,order.length()-1);   //ȥ��#�����
            return Double.parseDouble(dou);
        }

        if (order != null && order.equals("D000#")) { //���
            double p1 = InstructionsManager.doubleWithString(instruction, owner);
            double p2 = InstructionsManager.doubleWithString(instruction, owner);
            return DoubleManager.add(p1, p2, owner);
        }

        if (order != null && order.equals("D001#")) { //���
            double p1 = InstructionsManager.doubleWithString(instruction, owner);
            double p2 = InstructionsManager.doubleWithString(instruction, owner);
            return DoubleManager.dec(p1, p2, owner);
        }

        if (order != null && order.equals("D002#")) { //���
            double p1 = InstructionsManager.doubleWithString(instruction, owner);
            double p2 = InstructionsManager.doubleWithString(instruction, owner);
            return DoubleManager.mult(p1, p2, owner);
        }

        if (order != null && order.equals("D004#")) { //���
            double p1 = InstructionsManager.doubleWithString(instruction, owner);
            double p2 = InstructionsManager.doubleWithString(instruction, owner);
            return DoubleManager.div(p1, p2, owner);
        }

        if (order != null && order.equals("D010#")) { //intתdouble
            int p1 = InstructionsManager.integerWithString(instruction, owner);
            return DoubleManager.intToDouble(p1, owner);
        }
        return unexpectedInput();
    }

    private static double unexpectedInput() {  //��������
        return 0;
    }
}
