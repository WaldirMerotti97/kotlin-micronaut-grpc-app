package com.waldir.adapters.grpc.endpoint

import com.waldir.adapters.grpc.CommandRequestAccountStatementRequest
import com.waldir.adapters.grpc.CommandRequestAccountStatementResponse
import com.waldir.adapters.grpc.CommandRequestAccountStatementServiceGrpcKt
import com.waldir.adapters.grpc.StatementRequest
import com.waldir.application.services.StatementService
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
open class StatementEndpoint(private val service: StatementService) :
        CommandRequestAccountStatementServiceGrpcKt.CommandRequestAccountStatementServiceCoroutineImplBase() {

    override suspend fun sendCommandRequestAccountStatement(request: CommandRequestAccountStatementRequest): CommandRequestAccountStatementResponse {

        var response: CommandRequestAccountStatementResponse
        val responseBuilder = CommandRequestAccountStatementResponse.newBuilder()
        response = responseBuilder.setResponse("Enviado para procesamento").build()

        service.getStatement(request.personId, request.accountId)?.let { it ->
            it.forEach{
                responseBuilder.addStatement(StatementRequest.newBuilder()
                        .setSourceAccountId(it.sourceAccountId)
                        .setSourcePersonId(it.sourcePersonId)
                        .setDestinationPersonId(it.destinationPersonId)
                        .setDestinationAccountId(it.destinationAccountId)
                        .setTransferValue(it.transferValue)
                        .setDate(it.date.toString())
                        .setStatus(it.status)
                        .build()
                )
            }

            response = responseBuilder
                    .setResponse("Extrato OK")
                    .build()
            response
        }

        return response

    }

}