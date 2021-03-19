//package com.itau.adapters.kafka.consumers
//
//import io.micronaut.configuration.kafka.annotation.*
//import io.micronaut.messaging.Acknowledgement
//
//@KafkaListener(
//        groupId = "group-transfer-consumer-group",
//        offsetReset = OffsetReset.EARLIEST
//)
//class TransferredListener {
//
//    @Topic("statement-account")
//    fun receive(@KafkaKey key: String, statement: TransferredValue?, acknowledgement: Acknowledgement){
//
//
//        acknowledgement.ack()
//    }
//
//}