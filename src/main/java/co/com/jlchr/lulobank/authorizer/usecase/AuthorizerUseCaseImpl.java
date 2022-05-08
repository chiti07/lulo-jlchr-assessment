package co.com.jlchr.lulobank.authorizer.usecase;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Operation;
import co.com.jlchr.lulobank.authorizer.entity.Violations;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerRequest;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerResponse;
import co.com.jlchr.lulobank.authorizer.infra.port.out.persistence.AuthorizerData;
import co.com.jlchr.lulobank.authorizer.usecase.provider.AuthorizerProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    /**
     * Account Object
     */
    private Account account;

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
            if (authorizerData.getAccounts().isEmpty()) {
                responseBuilder.account(Account.builder()
                        .build())
                        .violations(Violations.ACCOUNT_NOT_INITIALIZED.getViolationString());

            } else {

            }
        }

        return responseBuilder.build();
    }
}
