package com.asms.common.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.service.config.spi.ConfigurationService;
import org.hibernate.service.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.service.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.schoolmgmt.dao.SchoolMgmtDaoImpl;

@SuppressWarnings("serial")
@Service
@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

	private DriverManagerConnectionProviderImpl provider = new DriverManagerConnectionProviderImpl();
	private static final Logger logger = LoggerFactory.getLogger(SchoolMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

//	@Autowired
//	private ExceptionHandler exceptionHandler;

	@Override
	public boolean isUnwrappableAs(Class arg0) {
		return provider.isUnwrappableAs(arg0);
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return provider.unwrap(arg0);
	}

	@Override
	public Connection getAnyConnection() throws SQLException {

		return provider.getConnection();
	}

	@Override
	public Connection getConnection(String tenantId) throws SQLException {
		Connection con = getAnyConnection();
		try {
			con.createStatement().execute("use " + tenantId);
			logger.info("Using " + tenantId + " as database schema");
		} catch (SQLException ex) {
			throw new HibernateException("Could not alter connection for specific schema");
		}
		return con;
	}

	@Override
	public void releaseAnyConnection(Connection con) throws SQLException {
		provider.closeConnection(con);

	}

	@Override
	public void releaseConnection(String tenantId, Connection con) throws SQLException {
		try {
			con.createStatement().execute("USE mysql");
			logger.info("Now, released " + tenantId);
		} catch (SQLException ex) {
			throw new HibernateException("Unable to reset");
		}
		provider.closeConnection(con);

	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor registry) {
		Map settings = registry.getService(ConfigurationService.class).getSettings();
		provider.configure(settings);
		provider.injectServices(registry);

	}

}
