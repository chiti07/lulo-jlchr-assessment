package co.com.jlchr.lulobank.authorizer.usecase;

import co.com.jlchr.lulobank.authorizer.entity.Operation;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerRequest;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerResponse;


public interface AuthorizerUseCase {

    AuthorizerResponse executeOperation(final AuthorizerRequest request, Operation operation);
}
