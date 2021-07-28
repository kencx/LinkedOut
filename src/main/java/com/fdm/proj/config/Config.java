package com.fdm.proj.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fdm.proj")
public class Config extends WebMvcConfigurerAdapter {


	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setPersistenceUnitName("SpringWebApp");
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPackagesToScan("com.fdm.springwebapp");
		entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
		return entityManagerFactory;
	}

	public DataSource dataSource() {
		
		// org.apache.commons.dbcp.BasicDataSource
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("kennethcheo");
		basicDataSource.setPassword("kenneth1996");

		return basicDataSource;
	}
	
	private Map<String, ?> hibernateJpaProperties() {

		HashMap<String, String> properties = new HashMap<>();

		properties.put("hibernate.archive.autodetection", "class, hbm");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");
		properties.put("hibernate.hbm2ddl.import_files", "insert-data.sql");
		properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		properties.put("hibernate.hbm2ddl.auto", "update"); // create, create-drop, validate, update
		properties.put("hibernate.c3p0.min_size", "2");
		properties.put("hibernate.c3p0.max_size", "5");
		properties.put("hibernate.c3p0.timeout", "300"); // 5mins
		properties.put("hibernate.c3p0.max_statements", "50");
		properties.put("hibernate.c3p0.idle_test_period", "3000");
		properties.put("hibernate.event.merge.entity_copy_observer", "allow");

		return properties;
	}
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
