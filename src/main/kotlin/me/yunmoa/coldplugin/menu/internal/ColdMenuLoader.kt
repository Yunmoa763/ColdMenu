package me.yunmoa.coldplugin.menu.internal

import me.yunmoa.coldplugin.menu.ColdMenu.plugin
import me.yunmoa.coldplugin.menu.util.DebugUtils.debug
import me.yunmoa.coldplugin.menu.util.ScriptUtils.eval
import org.bukkit.Bukkit
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Platform
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import taboolib.module.metrics.Metrics

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.internal
 *
 * @author Yunmoa
 * @since 2023/8/26 19:55
 */
object ColdMenuLoader {

    @Awake(LifeCycle.LOAD)
    fun onLoad() {
        console().sendMessage("")
        console().sendLang("Plugin-Loading", Bukkit.getServer().version)
        console().sendMessage("")
        Metrics(19660, plugin.description.version, Platform.BUKKIT)
    }

    @Awake(LifeCycle.ENABLE)
    fun onEnable() {
        console().sendLang("Plugin-Enabled")
        debug("Debug 模式已开启.")
        "print('JavaScript模块成功加载')".eval()
    }

    @Awake(LifeCycle.DISABLE)
    fun onDisable() {
        console().sendLang("Plugin-Disable")
    }
}