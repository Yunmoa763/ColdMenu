package me.yunmoa.coldplugin.menu.core.nashorn.script

import me.yunmoa.coldplugin.menu.internal.manager.HookerManager.nashornHooker
import org.bukkit.Bukkit
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import java.io.File
import java.io.Reader

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.core.nashorn.script
 *
 * @author Yunmoa
 * @since 2023/8/26 20:14
 */
class ScriptExpansion : CompiledScript {
    /**
     * 构建JavaScript脚本扩展
     *
     * @property reader js脚本文件
     * @constructor JavaScript脚本扩展
     */
    constructor(reader: Reader) : super(reader)

    /**
     * 构建JavaScript脚本扩展
     *
     * @property file js脚本文件
     * @constructor JavaScript脚本扩展
     */
    constructor(file: File) : super(file)

    /**
     * 构建JavaScript脚本扩展
     *
     * @property script js脚本文本
     * @constructor JavaScript脚本扩展
     */
    constructor(script: String) : super(script)

    override fun loadLib() {
        scriptEngine.eval(
            """
                const Bukkit = Packages.org.bukkit.Bukkit
                
                function sync(task) {
                    if (Bukkit.isPrimaryThread()) {
                        task()
                    } else {
                        scheduler.callSyncMethod(plugin, task)
                    }
                }
                
                function async(task) {
                    scheduler["runTaskAsynchronously(Plugin,Runnable)"](plugin, task)
                }
            """.trimIndent()
        )
    }

    /**
     * 执行指定函数
     *
     * @param function 函数名
     * @param expansionName 脚本名称(默认为unnamed)
     */
    fun run(function: String, expansionName: String = "unnamed") {
        if (nashornHooker.isFunction(scriptEngine, function)) {
            try {
                invoke(function, null)
            } catch (error: Throwable) {
                console().sendLang("ExpansionError", expansionName, function)
                error.printStackTrace()
            }
        }
    }
}