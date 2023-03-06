package com.handaltech.kafkaconsumer.kafka.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig(
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private val server: String,
    @Value("\${app.topics.load-test-topic}")
    private val loadTestTopic: String
) {
    //    @Bean
    fun kafkaAdmin(): KafkaAdmin = mutableMapOf<String, Any>()
        .apply {
            this[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        }.run { KafkaAdmin(this) }

    //    @Bean
    fun loadTestTopic(): NewTopic = NewTopic(loadTestTopic, 1, 1.toShort())
}