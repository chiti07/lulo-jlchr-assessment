package co.com.jlchr.lulobank.authorizer.infra.port.in.dto;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizerRequest {
    /**
     * Account information
     */
    private Account account;

    /**
     * Transaction information
     */
    private Transaction transaction;
}
