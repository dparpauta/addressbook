package dparpauta.model;

import dparpauta.exceptions.IncorrectDataException;


public enum Gender {

    MALE ("Male"),
    FEMALE ("Female");

    private String name;

    Gender (String name) {

        this.name = name;
    }

    public static Gender getGender(String name) {

        for (Gender gender : Gender.values()) {

            if (gender.name.equals(name)) {

                return gender;
            }
        }

        throw new IncorrectDataException("The gender could not be parsed " + name);
    }
}
