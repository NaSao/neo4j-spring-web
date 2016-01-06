package de.tuc.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuc.config.TestDatabaseConfiguration;
import de.tuc.domain.Person;
import de.tuc.repository.PersonRepository;

/**
 * Basic Tests on Test InProcessServer
 * Created by Panos on 31-Dec-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestDatabaseConfiguration.class)
public class BasicTests {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup() {

        Person greg = new Person("Greg");
        Person roy = new Person("Roy");
        Person craig = new Person("Craig");

        greg.worksWith(roy);
        greg.worksWith(craig);
        roy.worksWith(craig);

        personRepository.save(craig);
        personRepository.save(greg);
        personRepository.save(roy);
    }

    @Test
    public void test1() {

        Person greg = personRepository.findByName("Greg");
        Person roy = personRepository.findByName("Roy");
        Person craig = personRepository.findByName("Craig");
        assertEquals("Roy", roy.name);
        assertEquals(2, craig.getTeammates().size());
        assertEquals(2, greg.getTeammates().size());
        assertEquals(2, roy.getTeammates().size());
    }
}
