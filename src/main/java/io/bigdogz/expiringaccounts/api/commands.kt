package io.bigdogz.expiringaccounts.api

import org.axonframework.commandhandling.RoutingKey

class CreateAccount(
        @RoutingKey
        var id: String,
        val name: String,
        val balance: Double? = 0.0
)

data class AddFunds(
        @RoutingKey
        var accountId: String,
        var amount: Double
)

data class AddTransaction(
        @RoutingKey
        var accountId: String,
        var timestamp: Long? = System.currentTimeMillis(),
        val description: String,
        val sku: String,
        val amount: Double
)
