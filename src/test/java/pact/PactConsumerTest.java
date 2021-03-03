package pact;

import app.RestService;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.consumer.model.MockProviderConfig;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import com.google.api.client.http.HttpHeaders;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;

/**
 * @author Elbrus Garayev on 1/29/2021
 */
@PactFolder("pact-provider/src/test/resources/pacts")
public class PactConsumerTest {

    @Rule
    public PactProviderRule mockProvider
            = new PactProviderRule("test_provider", MockProviderConfig.LOCALHOST, 8080, this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Language", "en");
        headers.put("Content-Type", "application/json");

        //version 1 for creating body
        DslPart body = new PactDslJsonBody()
                .numberValue("salary", 1000)
                .stringValue("name", "Bill Doe")
                .object("contact")
                .stringValue("email", "billdoe@gmail.com")
                .stringValue("phone_number", "56565656")
                .closeObject();

        //version 2 for creating body
        DslPart bodyWithLambda = newJsonBody((jsonBody) -> {
            jsonBody.numberValue("salary", 1000);
            jsonBody.stringValue("name", "Bill Doe");
            jsonBody.object("contact", (contact) -> {
                contact.stringValue("email", "billdoe@gmail.com");
                contact.stringValue("phone_number", "56565656");
            });
        }).build();

        return builder
                .given("data count >= 0")
                .uponReceiving("a request for json data")
                .path("/users")
//                .matchPath("user/(employee|customer)")
                .headers(headers)
                .method("GET")
//                .body(bodyWithLambda)
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createPact")
    public void shouldFetchProducts() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Accept-Language", "en");
        httpHeaders.put("Content-Type", "application/json");
        RestService.get(httpHeaders, "/users");
//        RestService.get("/users");
    }
}

