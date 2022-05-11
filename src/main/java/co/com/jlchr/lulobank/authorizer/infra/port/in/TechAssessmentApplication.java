package co.com.jlchr.lulobank.authorizer.infra.port.in;

import co.com.jlchr.lulobank.authorizer.entity.Operation;
import co.com.jlchr.lulobank.authorizer.infra.port.in.dto.AuthorizerRequest;
import co.com.jlchr.lulobank.authorizer.usecase.AuthorizerUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Scanner;

import static java.util.Objects.nonNull;

@EntityScan("co.com.jlchr.lulobank.authorizer.entity")
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "co.com.jlchr.lulobank")
public class TechAssessmentApplication implements CommandLineRunner {

    /**
     * Authorizer Use Case.
     */
    private final AuthorizerUseCase authorizerUseCase;

    /**
     * Exit command.
     */
    private static final String EXIT_COMMAND = "exit";

    /**
     * Application Main Method
     *
     * @param args Application arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(TechAssessmentApplication.class, args);
    }

    /**
     * Execute the application.
     *
     * @param args args Application arguments.
     * @throws Exception If any exception is throwing.
     */
    @Override
    public void run(String... args) throws Exception {

        try (final var scanner = new Scanner(System.in)) {
            readLines(scanner);
        } catch (RuntimeException ex) {
            //TODO: Exception Handler
            ex.getMessage();
        }
    }

    private void readLines(Scanner scanner) throws JsonProcessingException {

        var stillRunning = true;

        try {
            while (stillRunning) {
                final var line = scanner.nextLine();
                if (EXIT_COMMAND.equals(line)) {
                    System.out.println("Shutting down");

                    stillRunning = false;
                    break;
                }
                final var objectMapper = new ObjectMapper();
                final var authorizerRequest = objectMapper.readValue(line, AuthorizerRequest.class);
                if (nonNull(authorizerRequest) && nonNull(authorizerRequest.getAccount())) {
                    System.out.println(objectMapper.writeValueAsString(authorizerUseCase.executeOperation(authorizerRequest, Operation.ACCOUNT_CREATION)));
                } else if (nonNull(authorizerRequest) && nonNull(authorizerRequest.getTransaction())) {
                    System.out.println(objectMapper.writeValueAsString(authorizerUseCase.executeOperation(authorizerRequest, Operation.TRANSACTION_AUTHORIZATION)));
                }
            }
        }catch (Exception e){
            System.out.println("The input typed is not valid or bad formatted.");
        }

    }
}
