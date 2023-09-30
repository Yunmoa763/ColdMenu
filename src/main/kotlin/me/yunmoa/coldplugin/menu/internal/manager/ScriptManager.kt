package me.yunmoa.coldplugin.menu.internal.manager

import me.yunmoa.coldplugin.menu.internal.manager.HookerManager.nashornHooker
import me.yunmoa.coldplugin.menu.nashorn.script.CompiledScript
import me.yunmoa.coldplugin.menu.util.ConfigUtils.getAllFiles
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import java.io.File

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.internal.manager
 *
 * @author Yunmoa
 * @since 2023/9/30 19:56
 */
object ScriptManager {
    /**
     * 获取公用ScriptEngine
     */
    val scriptEngine = nashornHooker.getNashornEngine()

    /**
     * 获取所有已编译的js脚本文件及路径
     */
    val compiledScripts = HashMap<String, CompiledScript>()

    init {
        // 加载全部脚本
        loadScripts()
    }

    /**
     * 加载全部脚本
     */
    private fun loadScripts() {
        for (file in getAllFiles("scripts")) {
            val fileName = file.path.replace("plugins${File.separator}ColdMenu${File.separator}scripts${File.separator}", "")
            try {
                compiledScripts[fileName] = CompiledScript(file)
            } catch (error: Throwable) {
                console().sendLang("InvalidScript", fileName)
                error.printStackTrace()
            }
        }
    }

    /**
     * 重载脚本管理器
     */
    fun reload() {
        compiledScripts.clear()
        loadScripts()
    }
}