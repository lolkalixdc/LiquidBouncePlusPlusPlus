/*
 * LiquidBounce++ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/PlusPlusMC/LiquidBouncePlusPlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.player

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.MotionEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo

import net.ccbluex.liquidbounce.utils.InventoryUtils
import net.ccbluex.liquidbounce.utils.timer.MSTimer
import net.ccbluex.liquidbounce.value.ListValue
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.minecraft.init.Items
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement
import net.minecraft.network.play.client.C09PacketHeldItemChange

@ModuleInfo(name = "KeepAlive", spacedName = "Keep Alive", description = "Tries to prevent you from dying.", category = ModuleCategory.PLAYER)
class KeepAlive : Module() {

    val modeValue = ListValue("Mode", arrayOf("/heal", "Soup"), "/heal")
    private val maxHealthValue = FloatValue("MaxHealth", 10F, 1F, 20F)
    private val HealDelay = IntegerValue("HealDelay", 150, 0, 10000, "ms")
    private val timer = MSTimer()

    @EventTarget
    fun onMotion(event: MotionEvent) {
        if (mc.thePlayer!!.health <= maxHealthValue.get()) {
            when (modeValue.get().lowercase()) {
                "/heal" -> {
                    if (!timer.hasTimePassed(HealDelay.get().toLong()))
                        return
                    mc.thePlayer.sendChatMessage("/heal")
                    timer.reset()
                }
                "soup" -> {
                    val soupInHotbar = InventoryUtils.findItem(36, 45, Items.mushroom_stew)

                    if (soupInHotbar != -1) {
                        mc.netHandler.addToSendQueue(C09PacketHeldItemChange(soupInHotbar - 36))
                        mc.netHandler.addToSendQueue(C08PacketPlayerBlockPlacement(mc.thePlayer.inventoryContainer.getSlot(soupInHotbar).stack))
                        mc.netHandler.addToSendQueue(C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem))
                    }
                }
            }
        }
    }
}
