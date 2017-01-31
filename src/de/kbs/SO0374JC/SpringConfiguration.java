package de.kbs.SO0374JC;

import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.kbs.SO1300JC.PWDecode;

@Configuration
@EnableTransactionManagement
public class SpringConfiguration  {

	private static final String url			= "jdbc:"+SpringValues.getDbms()+"://"+SpringValues.getAddr()+":"
												+SpringValues.getDbPort()+"/"+SpringValues.getDbName();
	private static final String user 		= SpringValues.getUser();
	private static 		 String password 	= SpringValues.getPw();
	private static final String driverClass	= SpringValues.getDriverClass();
	private static final String hibSchema	= "hibernate.default_schema";

	@SuppressWarnings("rawtypes")
	public static AbstractApplicationContext initiate (Class localConfiguration) {
		Class[] clazz						= { localConfiguration };
		return								SpringConfiguration.initiate(clazz);

	}
	@SuppressWarnings("rawtypes")
	public static AbstractApplicationContext initiate (Class[] localConfiguration) {
		ArrayList<Class> classList			= new ArrayList<>();
		classList.add						(SpringConfiguration.class);
		for (Class clazz : localConfiguration) 
			classList.add						(clazz);
		Class[] c							= new Class[classList.size()];
		AbstractApplicationContext context	= new AnnotationConfigApplicationContext
												  (classList.toArray(c));
		context.registerShutdownHook		();
		return 								context;
	}
	
	@Bean
	public DataSource dataSource() {

		if (password==null) {
			password 				= PWDecode.getPwDecoded(user);
			if  (password==null || password.startsWith("NULL")) {
				System.out.println		("FEHLER: Keine Anmeldung ohne Passwort");
				System.exit				(0);
			}
		}
		DriverManagerDataSource dataSrc					= new DriverManagerDataSource(url, user, password);
		dataSrc.setDriverClassName						(driverClass);
		
		return 											dataSrc;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean factory 	= new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter						(hibernateJpaVendorAdapter());
		factory.setPackagesToScan						(SpringValues.getScanPackages());
		Properties jpaProperties						= new Properties();
		jpaProperties.put								(hibSchema, SpringValues.getSchema());
		factory.setJpaProperties						(jpaProperties);
		factory.setDataSource							(dataSource());
		
		return 											factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager txManager					= new JpaTransactionManager();
		txManager.setEntityManagerFactory				(entityManagerFactory().getNativeEntityManagerFactory());
		
		return 											txManager;
	}
	
	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter () {
		
		HibernateJpaVendorAdapter vendorAdapter			= new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase						(Database.DB2);
		vendorAdapter.setGenerateDdl					(false);
		if (SpringValues.getShowSQL().equalsIgnoreCase("true"))
			vendorAdapter.setShowSql						(true);
		else
			vendorAdapter.setShowSql						(false);
		vendorAdapter.setDatabasePlatform				(SpringValues.getDbPlatform());
		
		return											vendorAdapter;
	}
	
//	@Bean
//	public Executor executor () {
//		return new Executor();
//	}
	
}
