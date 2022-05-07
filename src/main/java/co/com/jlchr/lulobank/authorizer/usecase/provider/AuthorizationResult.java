package co.com.jlchr.lulobank.authorizer.usecase.provider;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Violations;
import lombok.Builder;
import lombok.Getter;

/**
 * @author:
 */
@Getter
@Builder
public class AuthorizationResult {
    private final Account account;
    private final Violations violations;
}
