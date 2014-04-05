/**
 * Created by beardj on 4/5/14.
 */
package org.firebyte.generic.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author <a href="mailto:jeff.beard@datalogix.com">Jeff Beard</a>
 */
@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 200, nullable = false)
    private String lastName;


    public Person() {}

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
