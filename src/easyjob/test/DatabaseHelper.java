package easyjob.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import easyjob.model.DriverManagerConnectionPool;

/*Questa classe si occupa di popolare il db, facendo prima un delete dei vecchi dati e li reinserisce
 * per non creare scompenso tra più casi di test ripetuti */

public class DatabaseHelper {
	static Connection connect;
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		DriverManagerConnectionPool.setTest(true);
		 connect = DriverManagerConnectionPool.getConnection();
		ScriptRunner sr = new ScriptRunner(connect);
		java.io.Reader reader = new BufferedReader(new FileReader("popolamentotest.sql"));
		sr.runScript(reader);
		
		//DriverManagerConnectionPool.releaseConnection(connect);
		connect.close();
	}
}
