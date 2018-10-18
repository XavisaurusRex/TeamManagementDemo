package cat.devsofthecoast.teammanagementdemo.config

import cat.devsofthecoast.teammanagementdemo.core.async.thread.AsyncThreadExecutor
import cat.devsofthecoast.teammanagementdemo.core.async.ui.CNAAsyncPostExecutor
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig

class TMDAppConfig : BaseConfig{
    override fun getPostExecutor() = CNAAsyncPostExecutor()
    override fun getThreadExecutor() = AsyncThreadExecutor()
}