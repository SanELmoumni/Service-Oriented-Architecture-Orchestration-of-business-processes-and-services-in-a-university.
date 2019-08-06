package afak;
import java.sql.*;

public class Faculte {
	public String exist(String cin, String nom) {
		String apo="";
		final String fac = "fsr";

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/projetsoa?user=root&password=");
			Statement st = myConn.createStatement();
			ResultSet myset = st.executeQuery("SELECT cin FROM etudiant WHERE cin='"+cin+"'");
							while(myset.next()) {				
					if(cin.equals(myset.getString("cin")))
					return "étudiant déja inscrit dans la faculté !";
					
			}
			
			String nom2=nom.replaceAll(" ","");
			apo=nom2.substring(0, 3)+cin;
			
			PreparedStatement posted = myConn.prepareStatement("insert into etudiant (cin,nom,apogee,faculte) values ('"+cin+"', '"+nom+"', '"+apo+"', '"+fac+"')");
			posted.executeUpdate();
		}
		catch(Exception exc) {
			exc.printStackTrace();		
			return "Erreur de connexion à la base de donnée !";
		}
		return apo;
	}


}
