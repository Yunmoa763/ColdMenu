package me.yunmoa.coldplugin.menu.internal.manager

import me.yunmoa.coldplugin.menu.ColdMenu
import me.yunmoa.coldplugin.menu.ColdMenu.plugin
import me.yunmoa.coldplugin.menu.ColdMenu.setting
import me.yunmoa.coldplugin.menu.nashorn.script.CompiledScript
import me.yunmoa.coldplugin.menu.util.ConfigUtils.getFileOrNull
import me.yunmoa.coldplugin.menu.util.ConfigUtils.saveResourceNotWarn
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Platform
import taboolib.module.metrics.Metrics
import taboolib.module.metrics.charts.SingleLineChart
import java.io.File
import java.io.InputStreamReader
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.internal.manager
 *
 * @author Yunmoa
 * @since 2023/9/30 19:36
 */
object ConfigManager {

    /**
     * 获取配置文件
     */
    val debug: Boolean
        get() =  setting.getBoolean("Options.Debug", false)

    /**
     * 加载默认配置文件
     */
    @Awake(LifeCycle.INIT)
    fun saveResource() {
        if (getFileOrNull("workspace") == null) {
            plugin.saveResourceNotWarn("workspace${File.separator}CustomMenu.yml")
        }
        if (getFileOrNull("script") == null) {
            plugin.saveResourceNotWarn("script${File.separator}CustomFunction.js")
            plugin.saveResourceNotWarn("script${File.separator}CustomListener.js")
        }
        plugin.saveDefaultConfig()
    }

    /**
     * 重载配置管理器
     */
    fun reload() {
        setting.reload()
    }
}