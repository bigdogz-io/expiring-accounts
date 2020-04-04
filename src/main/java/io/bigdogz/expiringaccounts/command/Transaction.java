package io.bigdogz.expiringaccounts.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Transaction {
    private String transactionId;
    private Long timestamp;
    private String description;
    private Double amount;

    public Transaction(String transactionId, Long timestamp, String description, Double amount) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
    }
}
