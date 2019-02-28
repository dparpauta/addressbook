package dparpauta.repository;

import dparpauta.exceptions.ConfigurationException;
import dparpauta.exceptions.IncorrectDataException;
import dparpauta.model.Gender;
import dparpauta.model.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A class to read the data from the file (we can replace this with something else when we switch to another way of storing the data)
 */
@Slf4j
@AllArgsConstructor
public class PersonDao {

    private File file;

    public List<Person> getAll() {

        List<Person> personList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {

            log.debug("Reading the file {} line by line", file.getName());

            stream.forEach(entry -> {

                String[] data = entry.split(", ");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

                try {

                    personList.add(Person.builder().name(data[0]).gender(Gender.getGender(data[1]))
                            .dateOfBirth(dateFormat.parse(data[2])).build());
                }
                catch (ParseException e) {

                    log.error("The date {} could not be parsed for {}", data[2], data[0], e);
                }
                catch (IncorrectDataException e) {

                    log.error("The gender {} could not be parsed for {}", data[1], data[0], e);
                }
            });
        }
        catch (IOException e) {

            log.error("The file could not be read!", e);
            throw new ConfigurationException("The file you entered could not be read!");
        }

        return personList;
    }
}
