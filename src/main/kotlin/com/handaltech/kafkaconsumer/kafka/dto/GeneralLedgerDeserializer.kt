package com.handaltech.kafkaconsumer.kafka.dto

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import kotlin.text.Charsets.UTF_8

class GeneralLedgerDeserializer : Deserializer<GeneralLedgerData> {
    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): GeneralLedgerData? = objectMapper.readValue(
        String(data ?: throw SerializationException("Serialization error"), UTF_8),
        GeneralLedgerData::class.java
    )
}