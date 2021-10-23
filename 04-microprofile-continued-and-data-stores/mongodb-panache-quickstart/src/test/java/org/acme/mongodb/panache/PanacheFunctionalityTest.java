package org.acme.mongodb.panache;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.Collections;

@QuarkusTest
public class PanacheFunctionalityTest {

    @Test
    public void testPanacheMocking() {
        PanacheMock.mock(Person.class);

        // Mocked classes always return a default value
        Assertions.assertEquals(0, Person.count());

        // Now let's specify the return value
        Mockito.when(Person.count()).thenReturn(23L);
        Assertions.assertEquals(23, Person.count());

        // Now let's change the return value
        Mockito.when(Person.count()).thenReturn(42L);
        Assertions.assertEquals(42, Person.count());

        // Now let's call the original method
        Mockito.when(Person.count()).thenCallRealMethod();
        Assertions.assertEquals(0, Person.count());

        // Check that we called it 4 times
        PanacheMock.verify(Person.class, Mockito.times(4)).count();

        // Mock only with specific parameters
        Person p = new Person();
        Mockito.when(Person.findById(12L)).thenReturn(p);
        Assertions.assertSame(p, Person.findById(12L));
        Assertions.assertNull(Person.findById(42L));

        // Mock throwing
        Mockito.when(Person.findById(12L)).thenThrow(new WebApplicationException());
        Assertions.assertThrows(WebApplicationException.class, () -> Person.findById(12L));

        // We can even mock your custom methods
        Mockito.when(Person.findOrdered()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(Person.findOrdered().isEmpty());

        PanacheMock.verify(Person.class).findOrdered();
        PanacheMock.verify(Person.class, Mockito.atLeastOnce()).findById(Mockito.any());
        PanacheMock.verifyNoMoreInteractions(Person.class);
    }
}
