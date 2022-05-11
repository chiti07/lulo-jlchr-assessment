package co.com.jlchr.lulobank.authorizer.usecase.provider;


import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Transaction;

import java.util.Optional;

public interface AuthorizerProvider {

    /**
     * Find an account by ID.
     *
     * @param id Account ID.
     * @return {@link Optional} object with the found account.
     */
    Optional<Account> findAccountById(Long id);

    /**
     * Create an account.
     * @param account Account that will be created.
     * @return Account created.
     */
    Account createAccount(Account account);

    /**
     * Find a transaction by ID
     * @param id
     * @return
     */
    Optional<Transaction> findTransactionById(Long id);

    /**
     * Create a new Transaction
     * @param transaction
     * @return
     */
    Transaction createTransaction(Transaction transaction);

    /**
     * Set the new balance of the account
     * @param account
     * @return
     */
    void setCurrentAccount(Account account);

    /**
     * Get the current balance
     */
    Optional<Account> getCurrentAccount();

}
