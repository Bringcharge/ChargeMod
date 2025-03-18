package com.charge.chargemod.particle;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.joml.Vector3d;

import java.util.Locale;

public class ChargeBaseParticleType extends ParticleType implements ParticleOptions {
    // 我们需要存储的这几个参数,并且使用这几个参数创建的对于的粒子效果
    // 这几个参数从哪里来呢?,答案是从命令行的输入来
    private final Vector3d speed;
    private final double alpha;
    private final float diameter;
    private final String type;

    // 我们需要一个序列化和反序列化的东西,
    // 1. 目的是为了从命令行读取str的颜色,速度,等内容.
    // 2 是可以将将网络的发送的数据包中读取对应的颜色,速度.这里说的是客户端接受
    public static final Deserializer<ChargeBaseParticleType> DESERIALIZER = new Deserializer<ChargeBaseParticleType>() {
        // 第一个就是处理命令中的字符串
        // 输入的命令是这样的形式.
        // /particle 粒子 0 0 0 0 0 255 100 3
        // 其中的是 空格0空格0空格0空格0空格0空格255空格100空格3
        @Override
        public ChargeBaseParticleType fromCommand(ParticleType<ChargeBaseParticleType> pParticleType, StringReader pReader) throws CommandSyntaxException {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            pReader.expect(' '); // 处理空格
            double speedX = pReader.readDouble(); // 读取第一个0 
            pReader.expect(' '); // 处理空格
            double speedY = pReader.readDouble(); //....
            pReader.expect(' ');
            double speedZ = pReader.readDouble();
            pReader.expect(' ');
            double alpha = pReader.readDouble();
            pReader.expect(' ');
            float diameter = pReader.readFloat();
            pReader.expect(' ');
            String type = pReader.readString();
            return new ChargeBaseParticleType(new Vector3d(speedX, speedY, speedZ), alpha, diameter, type);
        }

        @Override
        public ChargeBaseParticleType fromNetwork(ParticleType<ChargeBaseParticleType> pParticleType, FriendlyByteBuf pBuffer) { // 这里是处理网络数据包的
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            double speedX = pBuffer.readDouble();
            double speedY = pBuffer.readDouble();
            double speedZ = pBuffer.readDouble();
            int alpha = pBuffer.readInt();
            float diameter = pBuffer.readFloat();
            String type = pBuffer.readUtf();
            return new ChargeBaseParticleType(new Vector3d(speedX, speedY, speedZ), alpha, diameter, type);
        }
    };

    // 构造方法
    public ChargeBaseParticleType(Vector3d speed, double alpha, float diameter, String type) {
        // super 第一个参数表示粒子不在人的视野范围内的时候是否渲染,可以选择不渲染
        // 第二个参数是我们的DESERIALIZER,帮我我们处理序列化的
        super(false, ChargeBaseParticleType.DESERIALIZER);
        this.speed = speed;
        this.alpha = alpha;
        this.diameter = diameter;
        this.type = type;
    }

    // 返回粒子的type等会我们注册
    @Override
    public ChargeBaseParticleType getType() {
        return this;        //改成注册的粒子
    }
    // 服务端发包要写的数据..
    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeDouble(this.speed.x);
        pBuffer.writeDouble(this.speed.y);
        pBuffer.writeDouble(this.speed.z);
        pBuffer.writeDouble(this.alpha);
        pBuffer.writeFloat(this.diameter);
        pBuffer.writeUtf(this.type);
    }
    // 写入成string，这玩意有问题，目前先不管，因为网络通信好像用不到它
    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%.2d %.2d %.2d %.2d %.2f %s",
                speed.x, speed.y, speed.z, alpha, diameter, type);
    }
    // codec,并不会用到他,不过还是给它返回一个数值吧
    @Override
    public Codec<ChargeBaseParticleType> codec() {
        return Codec.unit(this::getType);
    }


    // 自定义 Vector3d 的 Codec，虽然不知道有什么用
    public static final Codec<Vector3d> VECTOR3F_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.DOUBLE.fieldOf("x").forGetter(Vector3d::x),
                    Codec.DOUBLE.fieldOf("y").forGetter(Vector3d::y),
                    Codec.DOUBLE.fieldOf("z").forGetter(Vector3d::z)
            ).apply(instance, Vector3d::new)
    );

    public static final Codec<ChargeBaseParticleType> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        VECTOR3F_CODEC.fieldOf("speed").forGetter(options -> options.speed),
                        Codec.DOUBLE.fieldOf("alpha").forGetter(option -> option.alpha),
                        Codec.FLOAT.fieldOf("diameter").forGetter(option -> option.diameter),
                        Codec.STRING.fieldOf("type").forGetter(option -> option.type)
                ).apply(instance, ChargeBaseParticleType::new)

    );


    // 这里是我们自定义的粒子效果,我们需要返回一个粒子的provider,
    public Vector3d getSpeed() {
        return speed;
    }
    // 颜色,大小
    public double alpha() {
        return alpha;
    }

    public float getDiameter() {
        return diameter;
    }
}