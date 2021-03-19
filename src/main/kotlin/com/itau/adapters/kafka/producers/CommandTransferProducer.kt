package com.itau.adapters.kafka.producers

import com.itau.avro.CommandTransferValue
import io.micronaut.configuration.kafka.annotation.*
import org.apache.kafka.common.header.Headers

@KafkaClient(id = "command-transfer")
public interface CommandTransferProducer {

    @Topic("command-transfer")
    fun sendProduct(@KafkaKey brand: String,
                    transfer: CommandTransferValue?,
                    header: Headers?)

}
