package cat.devsofthecoast.teammanagementdemo.commons.config

import cat.devsofthecoast.teammanagementdemo.commons.core.async.thread.AsyncThreadExecutor
import cat.devsofthecoast.teammanagementdemo.commons.core.async.ui.CNAAsyncPostExecutor
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig

class TMDAppConfig : BaseConfig {
    override fun getPostExecutor() = CNAAsyncPostExecutor()
    override fun getThreadExecutor() = AsyncThreadExecutor()
}