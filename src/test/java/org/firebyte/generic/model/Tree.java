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
public class Tree {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String genus;

    @Column(length = 200, nullable = false)
    private String subGenus;

    @Column(length = 64, nullable = false)
    private String family;

    public Tree() {}

    public Tree(final String family, final String genus, final String subGenus) {
        this.genus    = genus;
        this.subGenus = subGenus;
        this.family   = family;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubGenus() {
        return subGenus;
    }

    public void setSubGenus(String subGenus) {
        this.subGenus = subGenus;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        if (!genus.equals(tree.genus)) return false;
        if (!family.equals(tree.family)) return false;
        if (!subGenus.equals(tree.subGenus)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genus.hashCode();
        result = 31 * result + subGenus.hashCode();
        result = 31 * result + family.hashCode();
        return result;
    }
}
