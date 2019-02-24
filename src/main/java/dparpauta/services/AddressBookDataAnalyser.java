package dparpauta.services;

import dparpauta.model.Gender;
import dparpauta.repository.PersonDao;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddressBookDataAnalyser {

    private PersonDao personDao;

    public long getNumberOfMales() {

        return personDao.getAll().stream().filter(person -> person.getGender() == Gender.MALE).count();
    }
}
