package afak2;
import java.sql.*;

public class Bibliotheeque {
	public String inscripB(String apog) {
		String cin ="";
		String nom="";
		
		
		try {
			// Premiere connextion avec la BDD de la facult� pour recuperer les informations de l'�tudiant necessaires pour son 
			// inscription � la biblioth�que.
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/projetsoa?user=root&password=");
			Statement st = myConn.createStatement();
			ResultSet myset = st.executeQuery("SELECT apogee,CIN,nom FROM etudiant WHERE apogee='"+apog+"'");
							
			while(myset.next()) {				
					if(apog.equals(myset.getString("apogee"))) {
						nom=myset.getString("nom");
						cin=myset.getString("CIN");
					}
			}
													
			Connection myConn2 = DriverManager.getConnection("jdbc:mysql://localhost/projetsoabiblio?user=root&password=");
			
			PreparedStatement posted = myConn2.prepareStatement("insert into etudiant (cin,nom,apogee) values ('"+cin+"', '"+nom+"', '"+apog+"')");
			posted.executeUpdate();
			return ("Inscris avec succ�s � la biblioth�que !");
			
		
		}
		catch(Exception exc) {
			exc.printStackTrace();		
				
			return "Erreur de connexion � la base de donn�e !";
		}
		
	}

}
