package co.com.jlchr.lulobank.authorizer.usecase;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Operation;
import co.com.jlchr.lulobank.authorizer.entity.Transaction;
import co.com.jlchr.lulobank.authorizer.entity.Violations;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerRequest;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerResponse;
import co.com.jlchr.lulobank.authorizer.usecase.provider.AuthorizerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation class of the Authorizer
 */
public class AuthorizerUseCaseImpl implements AuthorizerUseCase {

    private final AuthorizerProvider authorizerProvider;

    public AuthorizerUseCaseImpl(final AuthorizerProvider authorizerProvider) {
        this.authorizerProvider = authorizerProvider;
    }

    @Override
    public AuthorizerResponse executeOperation(final AuthorizerRequest request, Operation operation) {
        final var responseBuilder = AuthorizerResponse.builder();
        if (Operation.ACCOUNT_CREATION.equals(operation)) {
            authorizerProvider.findAccountById(request.getAccount().getId())
                    .ifPresentOrElse(account -> responseBuilder.account(Account.builder()
                                    .id(account.getId())
                                    .availableLimit(account.getAvailableLimit())
                                    .cardActivated(account.getCardActivated())
                                    .build())
                                    .violations(Violations.ACCOUNT_ALREADY_INITIALIZED.getViolationString()),
                            () -> {
                                final var account = authorizerProvider.createAccount(request.getAccount());
                                responseBuilder.account(Account.builder()
                                        .id(account.getId())
                                        .availableLimit(account.getAvailableLimit())
                                        .cardActivated(account.getCardActivated())
                                        .build());
                            });
        } else {
            System.out.println("Por implementar");
            /*authorizerProvider.findTransactionById(request.getTransaction().getId())
                    .ifPresentOrElse(transaction -> responseBuilder.account(Account.builder()
                            .build()),
            () -> {
                final var account = authorizerProvider.createTransaction(request.getTransaction());
                responseBuilder.account(Transaction.builder()
                        .id(account.getId())
                        .amount()
                        .availableLimit(.getAvailableLimit())
                        .cardActivated(account.getCardActivated())
                        .build());
                    });
*/

            //if(authorizerProvider.findAccountById(request.getAccount().getId()).)
            /*authorizerProvider.findAccountById(request.getAccount().getId())
                    .ifPresentOrElse(account -> responseBuilder.account(Account.builder()
                                    .availableLimit(account.getAvailableLimit())
                                    .cardActivated(account.getCardActivated())
                                    .build())
                                    .violations(Violations.INSUFFICIENT_LIMIT.getViolationString()),
                            () -> {
                                final var account = authorizerProvider.createAccount(request.getAccount());
                                responseBuilder.account(Account.builder()
                                        .availableLimit(account.getAvailableLimit())
                                        .cardActivated(account.getCardActivated())
                                        .build());
                            });

             */
        }

        return responseBuilder.build();
    }
}
