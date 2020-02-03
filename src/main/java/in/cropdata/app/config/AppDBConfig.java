/**
 * 
 */
package in.cropdata.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         01-Feb-2020
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "appEntityManagerFactory", transactionManagerRef = "appTransactionManager", basePackages = {
		"in.cropdata.app.repository" })
public class AppDBConfig {

	@Bean(name = "appDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public JdbcTemplate appJdbcTemplate(@Qualifier("appDataSource") DataSource appDataSource) {
		return new JdbcTemplate(appDataSource);
	}

	@Bean(name = "appEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("appDataSource") DataSource appDataSource) {
		return builder.dataSource(appDataSource).packages("in.cropdata.app.model").persistenceUnit("app-persitence")
				.build();
	}

	@Bean(name = "appTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory) {
		return new JpaTransactionManager(appEntityManagerFactory);
	}

}
