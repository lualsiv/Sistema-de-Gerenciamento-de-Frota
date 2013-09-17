package gerenciamentodefrota.test.utils;

import java.io.FileNotFoundException;
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
		if (connection != null) {
			System.out.println("Cant craete a Connection");
		} else {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/frotadeveiculos_teste",
					"postgres", "password");
		}
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (connection != null) {
			System.out.println("Cant craete a Connection");
		} else {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/frotadeveiculos_teste",
					username, password);
		}
		return connection;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		PrintWriter logWriter = null;
		try {
			logWriter = new PrintWriter("C:/work/vinay/Logfile.log");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return logWriter;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		try {
			DriverManager.setLogWriter(new PrintWriter("C:/work/vinay/Logfile.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
