package youcontribute.app.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import youcontribute.app.model.Repository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = RepositoryRepositoryTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("IntegrationTest")
@Testcontainers
class RepositoryRepositoryTestIT {

    @Container
    public static PostgreSQLContainer<?> postgres
            = new PostgreSQLContainer<>( DockerImageName.parse("postgres:16.2") );

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Test
    void it_should_find_all_repositories () throws Exception {

    	//given
        Repository repository1 = Repository.builder()
                .repository("repo1")
                .organization("org1")
                .build();
        Repository repository2 = Repository.builder()
                .repository("repo2")
                .organization("org2")
                .build();

        this.repositoryRepository.saveAll(Arrays.asList(repository1, repository2));
        this.entityManager.flush();
    	//when

        List<Repository> repos = this.repositoryRepository.findAll();

    	//then

        then( repos.size() ).isEqualTo(2);

        then( repos.get(0).getRepository() ).isEqualTo("repo1");
        then( repos.get(0).getOrganization() ).isEqualTo("org1");

        then( repos.get(1).getRepository() ).isEqualTo("repo2");
        then( repos.get(1).getOrganization() ).isEqualTo("org2");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + postgres.getJdbcUrl(),
                                        "spring.datasource.username=" + postgres.getUsername(),
                                        "spring.datasource.password=" + postgres.getPassword())
                    .applyTo(applicationContext.getEnvironment());


        }
    }


}