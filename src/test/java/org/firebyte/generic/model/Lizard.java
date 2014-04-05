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
public class Lizard {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String suborder;

    @Column(length = 200, nullable = false)
    private String family;

    @Column(length = 64, nullable = false)
    private String genus;

    public Lizard() {}

    public Lizard(final String suborder, final String family, final String genus) {
        this.suborder = suborder;
        this.family   = family;
        this.genus = genus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSuborder() {
        return suborder;
    }

    public void setSuborder(String suborder) {
        this.suborder = suborder;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lizard lizard = (Lizard) o;

        if (id != lizard.id) return false;
        if (!family.equals(lizard.family)) return false;
        if (!genus.equals(lizard.genus)) return false;
        if (!suborder.equals(lizard.suborder)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + suborder.hashCode();
        result = 31 * result + family.hashCode();
        result = 31 * result + genus.hashCode();
        return result;
    }
}
