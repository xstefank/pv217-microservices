package io.xstefank;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.xstefank.entity.Person;
import io.xstefank.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.Collections;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonRepositoryTest {

    @InjectMock
    PersonRepository personRepository;
    
    @Test
    public void testPanacheRepository() {
        // Mocked classes always return a default value
        Assertions.assertEquals(0, personRepository.count());

        // Now let's specify the return value
        Mockito.when(personRepository.count()).thenReturn(23l);
        Assertions.assertEquals(23, personRepository.count());

        // Now let's change the return value
        Mockito.when(personRepository.count()).thenReturn(42l);
        Assertions.assertEquals(42, personRepository.count());

        // Now let's call the original method
        Mockito.when(personRepository.count()).thenCallRealMethod();
        Assertions.assertEquals(4, personRepository.count());

        // Check that we called it 4 times
        Mockito.verify(personRepository, Mockito.times(4)).count();

        // Mock only with specific parameters
        Person p = new Person();
        Mockito.when(personRepository.findById(12l)).thenReturn(p);
        Assertions.assertSame(p, personRepository.findById(12l));
        Assertions.assertNull(personRepository.findById(42l));

        // Mock throwing
        Mockito.when(personRepository.findById(12l)).thenThrow(new WebApplicationException());
        Assertions.assertThrows(WebApplicationException.class, () -> personRepository.findById(12l));

        Mockito.when(personRepository.findAlive()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(personRepository.findAlive().isEmpty());

        // We can even mock your custom methods
        Mockito.verify(personRepository).findAlive();
        Mockito.verify(personRepository, Mockito.atLeastOnce()).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(personRepository);
    }
}
