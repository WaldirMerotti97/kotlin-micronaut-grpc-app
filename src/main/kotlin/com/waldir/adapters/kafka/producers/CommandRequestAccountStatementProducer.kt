package com.waldir.adapters.kafka.producers

import com.waldir.avro.CommandRequestAccountStatement
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.apache.kafka.common.header.Headers

@KafkaClient(id = "command-request-account-statement")
public interface CommandRequestAccountStatementProducer {

    @Topic("command-request-account-statement")
    fun sendCommandRequestAccountStatement(@KafkaKey key: String,
                                           requestAccountStatement: CommandRequestAccountStatement?,
                                           header: Headers?)

}

