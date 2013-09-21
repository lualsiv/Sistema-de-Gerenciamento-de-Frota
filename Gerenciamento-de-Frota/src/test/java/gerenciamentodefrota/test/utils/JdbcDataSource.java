package gerenciamentodefrota.test.utils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JdbcDataSource implements DataSource {

	Connection connection = null;

	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/frotadeveiculos_teste",
					"postgres", "password");
		}
		return connection;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
	}

	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		return null;
	}

}
