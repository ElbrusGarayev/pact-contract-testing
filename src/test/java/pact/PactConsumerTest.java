package pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;


import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Elbrus Garayev on 1/29/2021
 */
public class PactConsumerTest {

    @Rule
    public PactProviderRuleMk2 mockProvider
            = new PactProviderRuleMk2("test_provider", this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Language", "en");

        DslPart body = new PactDslJsonBody()
                .numberValue("salary", 1000)
                .stringValue("name", "Bill Doe")
                .object("contact")
                .stringValue("email", "billdoe@gmail.com")
                .stringValue("phone_number", "56565656")
                .closeObject();

//        DslPart bodyWithLambda = newJsonBody((jsonBody) -> {
//            jsonBody.numberValue("salary", 1000);
//            jsonBody.stringValue("name", "Bill Doe");
//            jsonBody.object("contact", (contact) -> {
//                contact.stringValue("email", "billdoe@gmail.com");
//                contact.stringValue("phone_number", "56565656");
//            });
//        }).build();

        return builder
                .given("data count > 0")
                .uponReceiving("a request for json data")
                .path("/product")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createPact")
    public void shouldFetchProducts() {
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(mockProvider.getUrl() + "/product", String.class);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getHeaders().get("Accept-Language")).contains("en"));
        assertEquals("Bill Doe", JsonPath.read(response.getBody(), "$.name"));
    }
}

