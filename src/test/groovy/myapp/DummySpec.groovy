package myapp

import grails.test.hibernate.HibernateSpec
import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty


class DummySpec extends HibernateSpec {

	void "test that dummy has no version property"() {
		when:
		new Dummy().version

		then:
		thrown MissingPropertyException
	}

	void "test that dummy.version is no persistent property"() {
		when:
		PersistentEntity persistentEntity = hibernateDatastore.mappingContext.getPersistentEntity(Dummy.class.name)
		List<PersistentProperty> persistentProperties = persistentEntity.getPersistentProperties()

		then:
		verifyAll {
			!persistentEntity.versioned
			!persistentEntity.version
			!persistentProperties.find { it.name == "version" }
		}
	}

}
