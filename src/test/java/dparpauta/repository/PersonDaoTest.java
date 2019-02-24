package dparpauta.repository;

import dparpauta.model.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class PersonDaoTest {

    private PersonDao target = new PersonDao();


    @Test
    public void testGetAll_whenFunctionCalled_thenItShouldReturnTheListOfPersonsFromTheAddressBookFile() {

        // when
        List<Person> result = target.getAll();

        // then
        assertEquals(5, result.size());
    }
}
