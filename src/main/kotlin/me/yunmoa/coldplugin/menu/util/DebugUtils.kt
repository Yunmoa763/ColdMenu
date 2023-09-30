package me.yunmoa.coldplugin.menu.util

import me.yunmoa.coldplugin.menu.internal.manager.ConfigManager
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.util
 *
 * @author Yunmoa
 * @since 2023/8/26 20:06
 */
object DebugUtils {
    fun debug(text: String) {
        if (ConfigManager.debug)
            console().sendLang("Plugin-Debug", text)
    }
}