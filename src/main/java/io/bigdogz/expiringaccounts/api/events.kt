package io.bigdogz.expiringaccounts.api

class AccountCreated (
        var id: String,
        val name: String
)

class FundsAdded(
        var accountId: String,
        var amount: Double
)

class TransactionAdded(
        var accountId: String,
        var timestamp: Long? = System.currentTimeMillis(),
        val description: String,
        val sku: String,
        val amount: Double
)
