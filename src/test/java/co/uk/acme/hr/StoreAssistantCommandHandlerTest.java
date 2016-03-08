package co.uk.acme.hr;

import co.uk.acme.shared.StoreAssistantId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * @author psamatt
 */
final public class StoreAssistantCommandHandlerTest {

    private FixtureConfiguration<StoreAssistant> fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(StoreAssistant.class);
        StoreAssistantCommandHandler commandHandler = new StoreAssistantCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void it_should_employ_store_assistant() {

        StoreAssistantId storeAssistantId = StoreAssistantId.generate();
        String name = "Joe Bloggs";

        fixture.given()
                .when(new EmployStoreAssistant(storeAssistantId, name))
                .expectVoidReturnType()
                .expectEvents(new StoreAssistantHasBeenEmployed(storeAssistantId, name));
    }
}
