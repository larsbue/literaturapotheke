package litap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import litap.database.DatabaseConnection;
import litap.model.User;

/*
 * Schnittstelle für die Datenbankzugriffe für Benutzer (User)
 */
public class UserRepository {

    /*
     * Findet eine Benutzer in der Datenbank anhand der "email"
     */
	public static User findByEmail(String email) {
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("email", email);
		List<User> users = find(filter);
		if(users.size() == 1)
			return users.get(0);
		return null;
	}

    /*
     * Speichert einen **neuen** Benutzer in der Datenbank ab
     */
	public static boolean add(User user) throws Exception {
		try {
            // Datum für die Datenbank umwandeln
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
	        try {
	            date = formatter.parse(user.getBirthdate());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO users "
					+ "(email, password, role, birthdate, land, profession, newsletter, status) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
			preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
			preparedStatement.setString(5, user.getLand());
			preparedStatement.setString(6, user.getProfession());
			preparedStatement.setBoolean(7, user.getNewsletter());
			preparedStatement.setString(8, user.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		finally {
		}
		return true;
	}

	/*
	 * gibt alle vorhanden Benutzer zurück
	 */
	public static List<User> findAll() {
		Map<String, String> filter = new HashMap<String, String>();
		return find(filter); // lerer Filter -> findet alles
	}
	
	/*
	 * Aktualisiert die Daten eines Benutzers. Die user.id MUSS gültig sein und in der datenbank vorhanden sein.
	 */
	public static boolean update(User u) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"UPDATE users "
					+ "SET email=?, "
					+ "password=?, "
					+ "role=?, "
					+ "land=?, "
					+ "profession=?, "
					+ "newsletter=?, "
					+ "status=? "
					+ "WHERE id = ?");
			preparedStatement.setString(1, u.getEmail());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getRole());
			preparedStatement.setString(4, u.getLand());
			preparedStatement.setString(5, u.getProfession());
			preparedStatement.setBoolean(6, u.getNewsletter());
			preparedStatement.setString(7, u.getStatus());

			preparedStatement.setInt(8, u.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * Gibt einen Benutzer mit der entsprechenden id zurück
	 */
	public static User findById(int id) {
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("id", String.valueOf(id));
		List<User> users = find(filter);
		if(users.size() == 1)
			return users.get(0);
		return null;
	}
	
	/*
	 * Sucht nach Benutzern mit den zutrefenden kritärien in "filter".
	 * Schlüßel und Wert von "filter" entspechen Datenbankspalte und Wert für die Datenbankanfrage.
	 * Alle "filter" paare werden mit "AND" verknüpft
	 */
	private static List<User> find(Map<String, String> filter) {
		List<User> users = new ArrayList<User>();
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM users WHERE 1=1 ";
			
			for (Map.Entry<String, String> entry : filter.entrySet()) {
				{
					sql += " AND " + entry.getKey() + "='" + entry.getValue() + "' ";
				}
			}
			
//			sql += "ORDER BY timestamp DESC ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet userResultSet = pstmt.executeQuery();
			while(userResultSet.next()) {
				User user = new User();
				user.setId(userResultSet.getInt("id"));
				user.setEmail(userResultSet.getString("email"));
				user.setPassword(userResultSet.getString("password"));
				user.setRole(userResultSet.getString("role"));
				user.setBirthdate(userResultSet.getString("birthdate"));
				user.setLand(userResultSet.getString("land"));
				user.setProfession(userResultSet.getString("profession"));
				user.setNewsletter(userResultSet.getBoolean("newsletter"));
				user.setStatus(userResultSet.getString("status"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/*
	 * Löscht einen Benutzer
	 */
	public static boolean remove(User user) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"DELETE FROM users WHERE id = ?");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
}
