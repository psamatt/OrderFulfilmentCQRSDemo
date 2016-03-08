package co.uk.acme.query.employees;

import co.uk.acme.hr.StoreAssistantHasBeenEmployed;
import co.uk.acme.shared.StoreAssistantId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author psamatt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springContext-test.xml")
public class EmployedStoreAssistantProjectorTest {

    @Autowired
    private EmployedStoreAssistantRepository repository;
    private EmployedStoreAssistantProjector target;

    @Before
    public void setUp() {
        repository.deleteAll();

        target = new EmployedStoreAssistantProjector();
        target.setEmployedStoreAssistantRepository(repository);
    }

    @Test
    public void it_should_add_employed_store_assistant() {
        StoreAssistantId id = StoreAssistantId.generate();
        String name = "Joe Bloggs";

        target.on(new StoreAssistantHasBeenEmployed(id, name));

        EmployedStoreAssistant assistant = repository.findOne(id.toString());

        assertThat(assistant, is(equalTo(getReadModel(id.toString(), name))));
    }

    private EmployedStoreAssistant getReadModel(String id, String name) {
        return new EmployedStoreAssistant(id, name);
    }
}