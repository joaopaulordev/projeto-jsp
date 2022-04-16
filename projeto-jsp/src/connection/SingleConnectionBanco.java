package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/projeto-jsp?autoReconnect=true";
	private static String senha = "admin";
	private static String user = "postgres";
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
