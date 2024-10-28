/*
 * LiquidBounce++ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/PlusPlusMC/LiquidBouncePlusPlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.hypixel;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.features.module.modules.movement.TargetStrafe;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class HypixelNew extends SpeedMode {

    public HypixelNew() {
        super("HypixelNew");
    }

    @Override
    public void onMotion() {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
        }
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onMove(MoveEvent event) {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
        }
    }

    @Override
    public void onJump(JumpEvent event) {
        // mc.thePlayer.motionX *= 1.190;
        // mc.thePlayer.motionZ *= 1.190;
        MovementUtils.strafe(0.4F);
    }
}
