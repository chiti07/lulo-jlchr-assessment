package co.com.jlchr.lulobank.authorizer.infra.port.out;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Transaction;
import co.com.jlchr.lulobank.authorizer.infra.port.out.persistence.AuthorizerData;
import co.com.jlchr.lulobank.authorizer.usecase.provider.AuthorizerProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizerProviderImpl implements AuthorizerProvider {


    /**
     * Authorizer Data.
     */
    private final AuthorizerData authorizerData;


    /**
     * Find an account by ID.
     *
     * @param id Account ID.
     * @return {@link Optional} object with the found account.
     */
    @Override
    public Optional<Account> findAccountById(Long id) {
        return authorizerData.getAccounts().stream()
                .filter(account -> id.equals(account.getId()))
                .findFirst();
    }

    /**
     * Create an account.
     *
     * @param account Account that will be created.
     * @return Account created.
     */
    @Override
    public Account createAccount(Account account) {
        authorizerData.getAccounts().add(account);
        return account;
    }

    /**
     * Find a transaction by ID
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return authorizerData.getTransactions().stream()
                .filter(transaction -> id.equals(transaction.getId()))
                .findFirst();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        authorizerData.getTransactions().add(transaction);
        return transaction;
    }


}