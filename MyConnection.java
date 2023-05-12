import java.sql.Connection;;

public class MyConnection{
	public Connection createConnection() {
		
		Connection connection=null;
		MysqlDataSource mds=new MysqlDataSource();
		
		mds.setServerName("localhost");
		mds.setPortNumber(3306);//obtain from xampp
		mds.setUser("root");
		mds.setPassword("");
		mds.setDatabaseName("feed_user_db");
		
		try {
			connection=mds.getconnection();
			
		}catch(SQLException ex) {
			Logger.getLogger(MY_CONNECTION.class.getName().log(Level.SEVERE,null,ex));
		}
		
		return connection;
	}
}