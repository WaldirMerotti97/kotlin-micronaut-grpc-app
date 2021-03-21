package com.waldir.application.services

import com.waldir.adapters.kafka.producers.CommandRequestAccountStatementProducer
import com.waldir.application.domain.Payment
import com.waldir.avro.CommandRequestAccountStatement
import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import java.util.*
import javax.inject.Singleton

@Singleton
@CacheConfig("statement-cache")
open class StatementService(private val commandRequestAccountStatementProducer: CommandRequestAccountStatementProducer) {

    @Cacheable(parameters = ["personId", "accountId"])
    open fun getStatement(personId: Int, accountId: Int): List<Payment>? {
        commandRequestAccountStatementProducer.sendCommandRequestAccountStatement(
                "myStatement" + UUID.randomUUID(),
                CommandRequestAccountStatement.newBuilder()
                        .setAccountId(accountId)
                        .setPersonId(personId)
                        .build(),
                RecordHeaders(
                        listOf(RecordHeader("correlationId", UUID.randomUUID().toString().toByteArray()))
                )
        )

        return null
    }

}