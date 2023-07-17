/*
 * Copyright ⓒ 2017 Brand X Corp. All Rights Reserved
 */
package com.specialpriceshop.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static org.springframework.data.redis.core.RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP;

/**
 * @author cadence
 */
@Configuration
@EnableRedisRepositories(basePackages = "com.specialpriceshop.auth.domain", enableKeyspaceEvents = ON_STARTUP)
public class RedisConfig {
}