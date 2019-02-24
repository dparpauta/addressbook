package dparpauta.services;

import dparpauta.model.Gender;
import dparpauta.model.Person;
import dparpauta.repository.PersonDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class AddressBookDataAnalyserTest {

    private AddressBookDataAnalyser target;
    private PersonDao personDao;


    @Before
    public void setUp() {

        personDao = mock(PersonDao.class);

        target = new AddressBookDataAnalyser(personDao);
    }


    @Test
    public void testGetNumberOfMales() throws ParseException {

        // given
        List<Person> people = getPeople();

        when(personDao.getAll()).thenReturn(people);

        // when
        long result = target.getNumberOfMales();

        // then
        assertEquals(1, result);
    }


    @Test
    public void testGetOldestPerson() throws ParseException {

        // given
        List<Person> people = getPeople();

        when(personDao.getAll()).thenReturn(people);

        // when
        Person result = target.getOldestPerson();

        // then
        assertEquals("Jane Doe", result.getName());
    }


    private List<Person> getPeople() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        List<Person> people = new ArrayList<>();
        people.add(Person.builder().name("Dan Parpauta").gender(Gender.MALE).dateOfBirth(dateFormat.parse("04/04/91")).build());
        people.add(Person.builder().name("Jane Doe").gender(Gender.FEMALE).dateOfBirth(dateFormat.parse("18/09/87")).build());

        return people;
    }
}
