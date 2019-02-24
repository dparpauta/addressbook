package dparpauta.services;

import dparpauta.exceptions.PersonNotFoundException;
import dparpauta.model.Gender;
import dparpauta.model.Person;
import dparpauta.repository.PersonDao;
import org.junit.Before;
import org.junit.Test;

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


    @Test
    public void testGetDaysDifferenceBetween() throws ParseException {

        // given
        List<Person> people = getPeople();

        when(personDao.getAll()).thenReturn(people);

        // when
        long result = target.getDaysDifferenceBetween("Dan", "Jane");
        long secondResult = target.getDaysDifferenceBetween("Jane", "Dan");

        // then
        assertEquals(365, result);
        assertEquals(365, secondResult);
    }


    @Test(expected = PersonNotFoundException.class)
    public void testGetDaysDifferenceBetween_whenThePersonIsNotFound_thenItShouldThrowException() throws ParseException {

        // given
        List<Person> people = getPeople();

        when(personDao.getAll()).thenReturn(people);

        // when
        target.getDaysDifferenceBetween("Not a person", "And another");
    }


    private List<Person> getPeople() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        List<Person> people = new ArrayList<>();
        people.add(Person.builder().name("Dan Parpauta").gender(Gender.MALE).dateOfBirth(dateFormat.parse("04/04/91")).build());
        people.add(Person.builder().name("Jane Doe").gender(Gender.FEMALE).dateOfBirth(dateFormat.parse("04/04/90")).build());

        return people;
    }
}
