## Generic JPA DAO

#### An Experiment with Generics

This project was originally intended as an experiement with Java Generics. The idea was that
Generics could be used to minimize code written for common data access use cases. This
project is a montage of an number of projects and articles. It depends on Spring and Hibernate
although it should be easily adapted to other JPA implementations as well.


#### Usage

This project is tested with Java 1.7 however I would expect it to work with 1.6 as well.

Client applications need to specify the following in their dependency configuration:

1. Spring Framework (tested with 4.0.3-RELEASE)
2. Hibernate (tested with 4.3.4.Final)
3. slf4j (tested with 1.4.2)

Client applications will need to provide their own JPA and Spring configuration.



##### JPA configuration

Client applications will need a persistence.xml that might look something like this:


```xml

<persistence xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
    version="2.0">

   <persistence-unit name="my-persistence-unit" transaction-type="RESOURCE_LOCAL">
       <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <class>org.firebyte.domain.User</class>
   </persistence-unit>
</persistence>
```


##### Spring configuration

You're Spring Configuration file might look something like this (entirely untested) configuration:

```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
            http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
       default-lazy-init="true">

    <bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>

    <!--  Use annotation based congfiguration -->
    <context:annotation-config />

    <!--  Tell Spring where to find annotations -->
    <context:component-scan base-package="org.firebyte.repository" />

    <!--
        Make Properties available to the application. You can use filters to
        configure for different environments unless you've gone all 12 Factor
        in which case use Puppet, Chef or a configuration service
        for Continuous Delivery and production environments.
    -->
    <context:property-placeholder location="classpath:database.properties" />

     <!-- DataSource: MySQL. Note the use of the properties found in the file -->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${hibernate.connection.driver_class}" />
        <property name="url" value="${jdbc.listing.url}" />
        <property name="username" value="${hibernate.connection.username}" />
        <property name="password" value="${hibernate.connection.password}" />
    </bean>

    <!-- Hibernate SessionFactory -->
    <bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="hibernateProperties">
            <value>
                hibernate.dialect=${hibernate.dialect}
                hibernate.query.substitutions=true 'Y', false 'N'
                hibernate.cache.use_second_level_cache=true
                hibernate.cache.provider_class=net.sf.ehcache.hibernate.SingletonEhCacheProvider
                hibernate.show_sql=true
                hibernate.format_sql=true
            </value>
        </property>
    </bean>

    <!-- Transaction management -->
    <tx:annotation-driven/>
    <bean id="transactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>

</beans>


```

##### Code

Using the GenerDAO JPA implementation might look something like this:

```java

// Save a person
GenericDAO<Person, Long> personDao = new GenericDAOJPA<Person, Long>(Person.class);

personDao.setEntityManager(entityManager);
Person person = new Person("Bob", "Smith");
person = personDao.save(person);

````



#### License

Copyright 2014 Jeff Beard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


#### Coda

That's it.