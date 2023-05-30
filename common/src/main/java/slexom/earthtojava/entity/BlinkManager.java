package slexom.earthtojava.entity;


import net.minecraft.util.math.random.Random;

public class BlinkManager {
    private final net.minecraft.util.math.random.Random random = Random.create();

    private int lastBlink = 0;
    private int nextBlinkInterval = this.random.nextInt(300) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public void tickBlink() {
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = this.random.nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }
}
