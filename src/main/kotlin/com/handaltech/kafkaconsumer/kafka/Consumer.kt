package com.handaltech.kafkaconsumer.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class Consumer {
    @KafkaListener(topics = ["\${app.topics.load-test-topic}"], groupId = "ppr")
    fun read(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
        try {
            println(consumerRecord.value())
            ack.acknowledge()
        } catch (ex: Exception) {
            println(ex)
        }
    }
}