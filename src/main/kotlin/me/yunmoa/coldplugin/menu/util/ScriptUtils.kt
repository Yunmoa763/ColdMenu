package me.yunmoa.coldplugin.menu.util

import me.yunmoa.coldplugin.menu.internal.manager.ScriptManager.scriptEngine
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import java.math.RoundingMode
import java.util.*

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.util
 *
 * @author Yunmoa
 * @since 2023/9/30 20:01
 */
object ScriptUtils {
    /**
     * 执行JS脚本
     *
     * @return 返回值
     */
    @JvmStatic
    fun String.eval(): Any {
        return scriptEngine.eval(this)
    }
}