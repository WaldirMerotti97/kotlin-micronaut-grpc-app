package com.waldir.adapters.grpc.endpoint

import com.waldir.adapters.grpc.CommandTransferServiceGrpcKt
import com.waldir.adapters.grpc.SendCommnandTransferRequest
import com.waldir.adapters.grpc.SendCommnandTransferResponse
import com.waldir.adapters.kafka.producers.CommandTransferProducer
import com.waldir.avro.CommandTransferValue
import io.micronaut.grpc.annotation.GrpcService
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import java.util.*

@GrpcService
open class TransferEndpoint(private val commandTransferProducer: CommandTransferProducer):
        CommandTransferServiceGrpcKt.CommandTransferServiceCoroutineImplBase() {

    override suspend fun sendCommandTransfer(request: SendCommnandTransferRequest): SendCommnandTransferResponse {
        commandTransferProducer.sendCommandTransfer(
                "my-transfer" + UUID.randomUUID(),
                CommandTransferValue(
                        request.sourcePersonId,
                        request.sourceAccountId,
                        request.destinationPersonId,
                        request.destinationAccountId,
                        request.transferValue.toFloat()
                ),
                RecordHeaders(
                        listOf(RecordHeader("correlationId", UUID.randomUUID().toString().toByteArray()))
                )
        )

        return SendCommnandTransferResponse.newBuilder()
                .setResponse("Pagamento em processamento")
                .build()
    }

}