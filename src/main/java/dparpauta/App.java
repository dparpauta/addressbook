package dparpauta;

import dparpauta.repository.PersonDao;
import dparpauta.services.AddressBookDataAnalyser;

import java.io.File;


public class App {

    public static void main(String[] args) {

        PersonDao personDao = new PersonDao(new File("/src/main/resources/AddressBook"));

        AddressBookDataAnalyser addressBookDataAnalyser = new AddressBookDataAnalyser(personDao);

        System.out.println("The number of males in the address book is " + addressBookDataAnalyser.getNumberOfMales());
        System.out.println("The oldest person is " + addressBookDataAnalyser.getOldestPerson().getName());
        System.out.println("Bill is older than Paul by " +
                addressBookDataAnalyser.getDaysDifferenceBetween("Bill", "Paul") + " days");
    }
}
