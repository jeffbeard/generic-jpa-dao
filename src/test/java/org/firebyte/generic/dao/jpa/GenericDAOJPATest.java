/**
 * Created by beardj on 4/5/14.
 */
package org.firebyte.generic.dao.jpa;

import junit.framework.TestCase;
import org.firebyte.generic.dao.GenericDAO;
import org.firebyte.generic.model.Lizard;
import org.firebyte.generic.model.Person;
import org.firebyte.generic.model.Tree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:jeff.beard@datalogix.com">Jeff Beard</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
@Transactional
public class GenericDAOJPATest extends TestCase {


    @PersistenceContext
    private EntityManager entityManager;

    public void setUp() throws Exception {
        super.setUp();
    }


    @Test
    public void genericDAOShouldSaveAPersonEntity() throws Exception {
        // Save a person

        GenericDAO<Person, Long> personDao = new GenericDAOJPA<Person, Long>(Person.class);

        personDao.setEntityManager(entityManager);
        Person person = new Person("Bob", "Smith");
        person = personDao.save(person);

        Person anotherPerson = personDao.get(person.getId());

        assertThat(anotherPerson, is(equalTo(person)));
    }

    @Test
    public void genericDAOShouldSaveALizardEntity() throws Exception {

        // Save a lizard
        GenericDAO<Lizard, Long> lizardLongGenericDAOJPA
            = new GenericDAOJPA<Lizard, Long>(Lizard.class);

        lizardLongGenericDAOJPA.setEntityManager(entityManager);
        Lizard lizard = new Lizard("Lacertilia", "Chamaeleonidae", "Bradypodion");
        lizard = lizardLongGenericDAOJPA.save(lizard);

        Lizard anotherLizard = lizardLongGenericDAOJPA.get(lizard.getId());

        assertThat(anotherLizard, is(equalTo(lizard)));

    }

    @Test
    public void genericDAOShouldSaveATreeEntity() throws Exception {
        // Save a Tree

        GenericDAO<Tree, Long> treeDao = new GenericDAOJPA<Tree, Long>(Tree.class);

        treeDao.setEntityManager(entityManager);
        Tree tree = new Tree("Pinaceae", "Pinus", "Strobus");
        tree = treeDao.save(tree);

        Tree anotherTree = treeDao.get(tree.getId());

        assertThat(anotherTree, is(equalTo(tree)));
    }
}
