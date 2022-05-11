package co.com.jlchr.lulobank.authorizer.infra.port.out.persistence;

import co.com.jlchr.lulobank.authorizer.entity.Account;
import co.com.jlchr.lulobank.authorizer.entity.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class AuthorizerData {

    @Singular("accounts")
    private final List<Account> accounts;

    @Singular("transactions")
    private final List<Transaction> transactions;

    @Setter
    private Account currentAccount;

    public AuthorizerData() {
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

}
