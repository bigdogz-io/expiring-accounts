package io.bigdogz.expiringaccounts.command;

import io.bigdogz.expiringaccounts.api.AccountCreated;
import io.bigdogz.expiringaccounts.api.AddFunds;
import io.bigdogz.expiringaccounts.api.AddTransaction;
import io.bigdogz.expiringaccounts.api.CreateAccount;
import io.bigdogz.expiringaccounts.api.FundsAdded;
import io.bigdogz.expiringaccounts.api.TransactionAdded;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Aggregate
@NoArgsConstructor
public class Account {

    @AggregateIdentifier
    private String id;
    private String name;
    private Double balance;
    private List<Transaction> transactions;

    @CommandHandler
    public Account(CreateAccount command) {
        log.info("handle.createAccount={}", command);
        AggregateLifecycle.apply(new AccountCreated(command.getId(), command.getName()));
    }

    @CommandHandler
    public void handle(AddFunds command) {
        log.info("handle.addFunds={}", command);
        AggregateLifecycle.apply(new FundsAdded(command.getAccountId(), command.getAmount()));
    }

    @CommandHandler
    public void handle(AddTransaction command) {
        log.info("handle.addTransaction={}", command);
        AggregateLifecycle.apply(
                new TransactionAdded(command.getAccountId(),
                        command.getTimestamp(),
                        command.getDescription(),
                        command.getSku(),
                        command.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountCreated event) {
        log.info("event.AccountCreated={}", event);
        id = event.getId();
        name = event.getName();
        balance = 0.0;
        transactions = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(FundsAdded event) {
        log.info("event.fundsAdded={}", event);
        balance += event.getAmount();
    }

    @EventSourcingHandler
    public void on(TransactionAdded event) {
        log.info("event.transactionAdded={}", event);
        transactions.add(Transaction.builder()
                .amount(event.getAmount())
                .description(event.getDescription())
                .transactionId(event.getAccountId())
                .timestamp(System.currentTimeMillis())
                .build());
    }
}
