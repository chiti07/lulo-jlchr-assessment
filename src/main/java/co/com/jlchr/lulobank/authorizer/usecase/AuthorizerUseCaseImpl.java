package co.com.jlchr.lulobank.authorizer.usecase;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Operation;
import co.com.jlchr.lulobank.authorizer.entity.Violations;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerRequest;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerResponse;
import co.com.jlchr.lulobank.authorizer.infra.port.out.persistence.AuthorizerData;
import co.com.jlchr.lulobank.authorizer.usecase.provider.AuthorizerProvider;

/**
 * Implementation class of the Authorizer
 */
public class AuthorizerUseCaseImpl implements AuthorizerUseCase {
    /**
     * Object from AuthorizerProvider interface
     */
    private final AuthorizerProvider authorizerProvider;

    /**
     * Object to get Data from the DTO Objedts
     */
    private final AuthorizerData authorizerData;

    public AuthorizerUseCaseImpl(final AuthorizerProvider authorizerProvider, final AuthorizerData authorizerData) {
        this.authorizerProvider = authorizerProvider;
        this.authorizerData = authorizerData;
    }

    /**
     * Method that processes the uses cases
     *
     * @param request
     * @param operation
     * @return
     */
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
            transactionValidation(request, responseBuilder);
        }

        return responseBuilder.build();
    }


    /**
     * Validates all the possible violations for the transaction
     * @param request
     * @param responseBuilder
     */
    private void transactionValidation(AuthorizerRequest request, AuthorizerResponse.AuthorizerResponseBuilder responseBuilder) {
        authorizerProvider.getCurrentAccount()
                .ifPresentOrElse(account -> {
                            limitValidation(request, responseBuilder, account);
                        },
                        () -> responseBuilder.account(Account.builder()
                                .build())
                                .violations(Violations.ACCOUNT_NOT_INITIALIZED.getViolationString()));
    }


    /**
     * Validates the limit violation
     * @param request
     * @param responseBuilder
     * @param account
     */
    private void limitValidation(AuthorizerRequest request, AuthorizerResponse.AuthorizerResponseBuilder responseBuilder, Account account) {
        Long availableBalance = (account.getAvailableLimit() - request.getTransaction().getAmount());
        if (availableBalance < 0) {
            responseBuilder.account(Account.builder()
                    .build())
                    .violations(Violations.INSUFFICIENT_LIMIT.getViolationString());
        } else {
            final var newAccount = authorizerProvider.createAccount(request.getAccount());
            responseBuilder.account(Account.builder()
                    .id(account.getId())
                    .availableLimit(availableBalance)
                    .cardActivated(account.getCardActivated())
                    .build());
        }
    }
}
