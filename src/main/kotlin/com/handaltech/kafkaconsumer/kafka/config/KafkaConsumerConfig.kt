package com.handaltech.kafkaconsumer.kafka.config

import com.handaltech.kafkaconsumer.kafka.dto.GeneralLedgerDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import java.util.*

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val servers: String
) {
    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> = mutableMapOf<String, Any>()
        .apply {
            this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
            this[ConsumerConfig.GROUP_ID_CONFIG] = "ppr"
            this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = GeneralLedgerDeserializer::class.java
            this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        }.run { DefaultKafkaConsumerFactory(this) }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any>? =
        ConcurrentKafkaListenerContainerFactory<String, Any>()
            .apply {
                this.consumerFactory = consumerFactory()
                this.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
                this.containerProperties.isSyncCommits = true
            }
}