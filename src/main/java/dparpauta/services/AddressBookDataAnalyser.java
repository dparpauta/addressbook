package dparpauta.services;

import dparpauta.model.Gender;
import dparpauta.model.Person;
import dparpauta.repository.PersonDao;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
public class AddressBookDataAnalyser {

    private PersonDao personDao;

    public long getNumberOfMales() {

        return personDao.getAll().stream().filter(person -> person.getGender() == Gender.MALE).count();
    }


    public Person getOldestPerson() {

        List<Person> people = personDao.getAll();

        Date date = people.stream().map(Person::getDateOfBirth).min(Date::compareTo).orElse(new Date());

        return people.stream().filter(person -> person.getDateOfBirth() == date).findFirst().orElse(Person.builder().build());
    }
}
