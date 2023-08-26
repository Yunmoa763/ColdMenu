package me.yunmoa.coldplugin.menu.internal.manager

import me.yunmoa.coldplugin.menu.core.nashorn.hook.NashornHooker
import me.yunmoa.coldplugin.menu.core.nashorn.hook.impl.LegacyNashornHookerImpl
import me.yunmoa.coldplugin.menu.core.nashorn.hook.impl.NashornHookerImpl

/**
 * ColdMenu
 * me.yunmoa.coldplugin.menu.internal.manager
 *
 * @author Yunmoa
 * @since 2023/8/26 20:14
 */
/**
 * 插件兼容管理器, 用于尝试与各个软依赖插件取得联系
 */
object HookerManager {
    private fun check(clazz: String): Boolean {
        return try {
            Class.forName(clazz)
            true
        } catch (error: Throwable) {
            false
        }
    }

    val nashornHooker: NashornHooker =
        when {
            // jdk自带nashorn
            check("jdk.nashorn.api.scripting.NashornScriptEngineFactory") -> LegacyNashornHookerImpl()
            // 主动下载nashorn
            else -> NashornHookerImpl()
        }
}