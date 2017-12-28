package com.pewa;

import com.pewa.common.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by phonik on 2017-12-27.
 */
public class PersonTest {

    @Test
    @Disabled
    public void checkSorting() {
        Person one = new Person(851, "Lana Wachowski", "writer" );
        Person two = new Person(854, "Lilly Wachowski", "writer");
        Person three = new Person(851, "Lana Wachowski", "director");

        System.out.println(one.equals(two));
        System.out.println(two.equals(one));
        System.out.println(three.equals(one));

        Set<Person> grupa = new HashSet<>();
        grupa.add(one);
        grupa.add(two);
        grupa.add(three);
        System.out.println(grupa);



    }
}
