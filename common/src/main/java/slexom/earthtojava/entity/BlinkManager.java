package slexom.earthtojava.entity;

import java.util.Random;

public class BlinkManager {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(300) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public void tickBlink() {
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }
}
