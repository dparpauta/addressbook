package dparpauta.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class Person {

    private String name;
    private Gender gender;
    private Date dateOfBirth;
}
