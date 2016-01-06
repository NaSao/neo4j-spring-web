package de.tuc.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableNeo4jRepositories(basePackages = "de.tuc.repository", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableTransactionManagement
public class TestDatabaseConfiguration extends Neo4jConfiguration {

    private final Logger log = LoggerFactory.getLogger(TestDatabaseConfiguration.class);

    @Override
    @Bean
    public Neo4jServer neo4jServer() {
        log.info("Initialising Test Server Connection");
        return new RemoteServer("http://localhost:7474", "neo4j", "neo4j_322nyq");
        
    }

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        log.info("Initialising Test Session Factory");
        return new SessionFactory("de.tuc.domain");
    }

    @Override
    @Bean
    public Session getSession() throws Exception {
        log.info("Initialising Test Session Bean");
        return super.getSession();
    }
}
