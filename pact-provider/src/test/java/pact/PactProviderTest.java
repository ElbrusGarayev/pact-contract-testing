package pact;

import au.com.dius.pact.core.model.annotations.PactFolder;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactUrl;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import org.junit.runner.RunWith;

/**
 * @author Elbrus Garayev on 1/30/2021
 */
@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("test_provider") // Set up name of tested provider
@PactFolder("target/pacts") // Point where to find pacts (See also section Pacts source in documentation)
//@PactUrl(urls = {"file:///C:/Users/004460/IdeaProjects/pact-contract-testing/target/pacts/test_consumer-test_provider.json"})
public class PactProviderTest {

    @State("") // Method will be run before testing interactions that require "with-data" state
    public void hammerSmith() {
        System.out.println("There is a bus with number 613 arriving to Hammersmith bus station" );
    }

    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget(8111); // Out-of-the-box implementation of Target (for more information take a look at Test Target section)
}
