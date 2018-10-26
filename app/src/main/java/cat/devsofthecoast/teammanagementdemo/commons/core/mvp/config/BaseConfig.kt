package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.ErrorHandler
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.PostExecutor
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.ThreadExecutor

interface BaseConfig {
    fun getThreadExecutor(): ThreadExecutor
    fun getPostExecutor(): PostExecutor
    fun getErrorHandler(): ErrorHandler? = null
}