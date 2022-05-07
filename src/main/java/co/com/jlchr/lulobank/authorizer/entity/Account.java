package co.com.jlchr.lulobank.authorizer.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    /**
     * Account Id.
     */
    private Long id;

    /**
     * Account card is activated.
     */
    @JsonProperty("active-card")
    private Boolean cardActivated;

    /**
     * Account available limit.
     */
    @JsonProperty("available-limit")
    private Long availableLimit;
}
