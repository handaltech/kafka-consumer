package com.handaltech.kafkaconsumer.kafka.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GeneralLedgerData(
    @JsonProperty("threadId")
    val threadId: String,
    @JsonProperty("customerId")
    val customerId: String,
    @JsonProperty("movementDate")
    val movementDate: String,
    @JsonProperty("transactionUuid")
    val transactionUuid: String,
    @JsonProperty("value")
    val value: String,
    @JsonProperty("message")
    val message: String
)
