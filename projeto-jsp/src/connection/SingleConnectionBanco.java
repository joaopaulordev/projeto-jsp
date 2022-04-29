package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://ec2-23-20-224-166.compute-1.amazonaws.com:5432/d8cajd1mfqn27m?sslmode=require&autoReconnect=true";
	private static String senha = "4e8df37c37a479588e12feb7e129f44c9c3ccf58b120da7eee153f9f0e6aec31";
	private static String user = "bxabmsikamsvas";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); //carrega o driver de conexãp
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);  //so existir alteracao no banco com nosso comando
				System.out.println("Banco Conectado!!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
