package io.bigdogz.expiringaccounts.api;

import io.bigdogz.expiringaccounts.command.Account;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class AccountEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public AccountEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping(
            value = "/account",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<String> createAccount(
            @RequestBody
            CreateAccount createAccount
    ) {
        return commandGateway.send(createAccount);
    }

    @PutMapping(
            value = "/account",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addFunds(
            @RequestBody
            final AddFunds addFunds
    ) {
        commandGateway.send(addFunds);
    }

    @GetMapping(
            value = "/account/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<Account> getAccountById(@PathVariable(value = "accountId") final String accountId) {
        return queryGateway.query("accountId", Account.class);
    }

    @PostMapping(
            value = "/account/{accountId}/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addTransaction(
            @PathVariable(value = "accountId")
            final String accountId,
            @RequestBody
            final AddTransaction transaction) {
        transaction.setAccountId(accountId);
        commandGateway.send(transaction);
    }
}
