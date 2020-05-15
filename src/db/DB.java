package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conexao = null;
	
	public static Properties loadProperties() {
		try(FileInputStream arquivo = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(arquivo);
			return properties;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public static Connection getConexao() {
		if(conexao==null) {
			Properties properties = loadProperties();
			String url = properties.getProperty("dburl");
			try {
				conexao = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conexao;
	}
	
	public static void closeConexao() {
		if(conexao!=null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
