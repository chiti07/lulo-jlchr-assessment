package co.com.jlchr.lulobank.authorizer.infra.port.in.dto;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizerResponse {
    /**
     * Account information.
     */
    private final Account account;

    /**
     * Operation violations.
     */
    @Singular("violations")
    private final List<String> violations;
}
