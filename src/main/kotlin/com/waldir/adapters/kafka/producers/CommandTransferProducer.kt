package com.waldir.adapters.kafka.producers

import com.waldir.avro.CommandTransferValue
import io.micronaut.configuration.kafka.annotation.*
import org.apache.kafka.common.header.Headers

@KafkaClient(id = "command-transfer")
public interface CommandTransferProducer {

    @Topic("command-transfer")
    fun sendCommandTransfer(@KafkaKey brand: String,
                            transfer: CommandTransferValue?,
                            header: Headers?)

}
