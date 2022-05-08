package co.com.jlchr.lulobank.authorizer.application.config;

import co.com.jlchr.lulobank.authorizer.infra.port.out.persistence.AuthorizerData;
import co.com.jlchr.lulobank.authorizer.usecase.AuthorizerUseCase;
import co.com.jlchr.lulobank.authorizer.usecase.AuthorizerUseCaseImpl;
import co.com.jlchr.lulobank.authorizer.usecase.provider.AuthorizerProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author:
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizerConfig {

    @Bean
    public AuthorizerUseCase getAuthorizerUseCase(final AuthorizerProvider authorizerProvider, final AuthorizerData authorizerData){
        return new AuthorizerUseCaseImpl(authorizerProvider, authorizerData );
    }
}
