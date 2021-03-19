package com.itau.adapters.kafka.producers

import com.itau.avro.CommandRequestAccountStatement
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.apache.kafka.common.header.Headers

@KafkaClient(id = "command-request-account-statement")
public interface CommandRequestAccountStatementProducer {

    @Topic("command-request-account-statement")
    fun sendProduct(@KafkaKey brand: String,
                    requestAccountStatement: CommandRequestAccountStatement?,
                    header: Headers?)

}

