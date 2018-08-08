package messageNotification.configs;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig 
{	
	@Bean
	public DataSource dataSource() {
		
		HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(100);
        ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        ds.addDataSourceProperty("url","jdbc:postgresql://localhost:5432/m_notification");
        ds.addDataSourceProperty("user", "mNotify");
        ds.addDataSourceProperty("password", "mNotify");
//        ds.addDataSourceProperty("cachePrepStmts", true);
//        ds.addDataSourceProperty("prepStmtCacheSize", 250);
//        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//        ds.addDataSourceProperty("useServerPrepStmts", true);
        
        return ds;
	}
}
