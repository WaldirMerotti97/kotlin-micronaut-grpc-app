package com.itau.adapters.kafka.consumers

import com.itau.avro.AccountStatement
import io.micronaut.configuration.kafka.annotation.*
import io.micronaut.messaging.Acknowledgement

@KafkaListener(
        groupId = "group-statement-consumer-group",
        offsetReset = OffsetReset.EARLIEST
)
class StatementListener {

    @Topic("statement-account")
    fun receive(@KafkaKey key: String, statement: AccountStatement?, acknowledgement: Acknowledgement){


        acknowledgement.ack()
        acknowledgement.ack()
        acknowledgement.ack()
    }

}

