package dparpauta.repository;

import dparpauta.exceptions.ConfigurationException;
import dparpauta.model.Person;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PersonDaoTest {

    @Test
    public void testGetAll_whenFunctionCalled_thenItShouldReturnTheListOfPersonsFromTheAddressBookFile() {

        // given
        PersonDao target = new PersonDao(new File("src/test/resources/AddressBook"));

        // when
        List<Person> result = target.getAll();

        // then
        assertEquals(5, result.size());
    }


    @Test(expected = ConfigurationException.class)
    public void testGetAll_whenTheFileDoesNotExist_thenItShouldThrowConfigurationException() {

        // given
        PersonDao target = new PersonDao(new File("src/test/resources/SomethingThatIsNotThere"));

        // when
        target.getAll();
    }


    @Test
    public void testGetAll_whenTheDateIsNotValid_ThenItShouldExcludeThatEntry() {

        // given
        PersonDao target = new PersonDao(new File("src/test/resources/AddressBookInvalidDate"));

        // when
        List<Person> result = target.getAll();

        // then
        assertTrue(result.isEmpty());
    }


    @Test
    public void testGetAll_whenTheGenderIsNotValid_thenItShouldExcludeTheEntry() {

        // given
        PersonDao target = new PersonDao(new File("src/test/resources/AddressBookInvalidGender"));

        // when
        List<Person> result = target.getAll();

        // then
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }
}
