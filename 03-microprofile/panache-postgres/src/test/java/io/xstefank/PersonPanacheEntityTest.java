package io.xstefank;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.xstefank.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.Collections;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonPanacheEntityTest {

    @Test
    public void testPanacheMock() {
        PanacheMock.mock(Person.class);

        // Mocked classes always return a default value
        Assertions.assertEquals(0, Person.count());

        // Now let's specify the return value
        Mockito.when(Person.count()).thenReturn(23l);
        Assertions.assertEquals(23, Person.count());

        // Now let's change the return value
        Mockito.when(Person.count()).thenReturn(42l);
        Assertions.assertEquals(42, Person.count());

        // Now let's call the original method
        // Note that this will include import.sql!!!
        Mockito.when(Person.count()).thenCallRealMethod();
        Assertions.assertEquals(4, Person.count());

        // Check that we called it 4 times
        PanacheMock.verify(Person.class, Mockito.times(4)).count();

        // Mock only with specific parameters
        Person p = new Person();
        Mockito.when(Person.findById(12l)).thenReturn(p);
        Assertions.assertSame(p, Person.findById(12l));
        Assertions.assertNull(Person.findById(42l));

        // Mock throwing
        Mockito.when(Person.findById(12l)).thenThrow(new WebApplicationException());
        Assertions.assertThrows(WebApplicationException.class, () -> Person.findById(12l));

        // We can even mock your custom methods
        Mockito.when(Person.findAlive()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(Person.findAlive().isEmpty());

        // Mocking a void method
        Person.deleteMartin();

        // Make it throw
        PanacheMock.doThrow(new RuntimeException("Martin")).when(Person.class).deleteMartin();
        try {
            Person.deleteMartin();
            Assertions.fail();
        } catch (RuntimeException x) {
            Assertions.assertEquals("Martin", x.getMessage());
        }

        // Back to doNothing
        PanacheMock.doNothing().when(Person.class).deleteMartin();
        Person.deleteMartin();

        // Make it call the real method
        PanacheMock.doCallRealMethod().when(Person.class).deleteMartin();
        try {
            Person.deleteMartin();
            Assertions.fail("void");
        } catch (Throwable x) {
            Assertions.assertEquals("void", x.getMessage());
        }

        PanacheMock.verify(Person.class).findAlive();
        PanacheMock.verify(Person.class, Mockito.atLeast(4)).deleteMartin();
        PanacheMock.verify(Person.class, Mockito.atLeastOnce()).findById(Mockito.any());
    }

}
