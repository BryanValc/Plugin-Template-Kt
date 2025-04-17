package com.bryanvalc.pluginTemplateKt.common.modules

import com.bryanvalc.pluginTemplateKt.common.config.CacheConfig
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import java.time.Duration

class CacheManager(
    private val config: CacheConfig
) {
    private val caches = mutableMapOf<String, Cache<*, *>>()

    fun <K : Any, V : Any> createCache(
        name: String,
        maxSize: Int = config.maxSize,
        expireAfterAccess: Duration = Duration.ofMinutes(config.minutesDuration.toLong())
    ): Cache<K, V> {
        return Caffeine.newBuilder()
            .maximumSize(maxSize.toLong())
            .expireAfterAccess(expireAfterAccess)
            .build<K, V>()
            .also { caches[name] = it }
    }

    fun <K : Any, V : Any> getCache(name: String): Cache<K, V>? {
        return caches[name] as? Cache<K, V>
    }
}