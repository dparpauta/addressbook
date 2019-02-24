package dparpauta.services;

import dparpauta.exceptions.PersonNotFoundException;
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


    public long getDaysDifferenceBetween(String nameOfFirstPerson, String nameOfSecondPerson) {

        List<Person> people = personDao.getAll();

        Person firstPerson = people.stream().filter(person -> person.getName().contains(nameOfFirstPerson)).findFirst()
                .orElseThrow(() -> new PersonNotFoundException("The person you tried to find is not in the address book"));

        Person secondPerson = people.stream().filter(person -> person.getName().contains(nameOfSecondPerson)).findFirst()
                .orElseThrow(() -> new PersonNotFoundException("The person you tried to find is not in the address book"));

        long timeDifference = firstPerson.getDateOfBirth().getTime() - secondPerson.getDateOfBirth().getTime();

        return timeDifference > 0 ? timeDifference / (1000*60*60*24L) : Math.abs(timeDifference) / (1000*60*60*24L);
    }
}
