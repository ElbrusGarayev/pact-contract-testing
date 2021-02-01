package pact;


import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Elbrus Garayev on 1/30/2021
 */
@Provider("test_provider")
@PactFolder("src/test/resources/pacts")
public class PactProviderTest {

//    @BeforeEach
//    void before(PactVerificationContext context) {
//        context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
//    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("data count >= 0")
    public void toGetState() { }
}

