/*
 * LiquidBounce++ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/PlusPlusMC/LiquidBouncePlusPlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.ListValue

import net.minecraft.util.ResourceLocation

@ModuleInfo(name = "Cape", description = "LiquidBounce+ capes.", category = ModuleCategory.RENDER)
class Cape : Module() {

    val styleValue = ListValue("Style", arrayOf("Dark", "Darker", "Light", "Special1", "Special2"), "Dark")

    private val capeCache = hashMapOf<String, CapeStyle>()

    fun getCapeLocation(value: String): ResourceLocation {
        if (capeCache[value.uppercase()] == null) {
            try {
                capeCache[value.uppercase()] = CapeStyle.valueOf(value.uppercase())
            } catch (e: Exception) {
                capeCache[value.uppercase()] = CapeStyle.DARK
            }
        }
        return capeCache[value.uppercase()]!!.location
    }

    enum class CapeStyle(val location: ResourceLocation) {
        DARK(ResourceLocation("liquidbounce+/cape/dark.png")),
        DARKER(ResourceLocation("liquidbounce+/cape/darker.png")),
        LIGHT(ResourceLocation("liquidbounce+/cape/light.png")),
        SPECIAL1(ResourceLocation("liquidbounce+/cape/special1.png")),
        SPECIAL2(ResourceLocation("liquidbounce+/cape/special2.png"))
    }

    override val tag: String
        get() = styleValue.get()

}