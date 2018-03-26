package litap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import litap.database.DatabaseConnection;
import litap.model.Literature;

/*
 * Schnittstelle für die Datenbankzugriffe für Werke (Literature)
 */
public class LiteratureRepository {
	
	/*
	 * Speichert ein "neues" Werk ab
	 */
	public static boolean persist(Literature literature) throws SQLException{
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO literature "
					+ "(author, title, content, motivation, user_id, source, postdate, formating, topic, application, genre, admincomment, status) "
					+ "VALUES(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, literature.getAuthor());
			preparedStatement.setString(2, literature.getTitle());
			preparedStatement.setString(3, literature.getContent());
			preparedStatement.setString(4, literature.getMotivation());
			preparedStatement.setInt(5, literature.getUser_id());
			preparedStatement.setString(6, literature.getSource());
//			preparedStatement.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(7, literature.getFormating());
			preparedStatement.setString(8, literature.getTopic());
			preparedStatement.setString(9, literature.getApplication());
			preparedStatement.setString(10, literature.getGenre());
			preparedStatement.setString(11, literature.getAdmincomment());
			preparedStatement.setString(12, literature.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
		finally {
		}
		return true;
	}
	
	/*
	 * Sucht entprechent nach allen Werken wie im "filter" angegeben.
	 * Der Filterschlüßel kann entweder ein Datenbankspaltenname sein
	 * oder title_like,author_like,title_or_author_like,complain für
	 * spezielle Filteranweisungen.
	 */
	public static List<Literature> find(Map<String, String> filter) {
		List<Literature> literature = new ArrayList<Literature>();
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "SELECT *, l.user_id AS l_user_id FROM literature l ";
			
			sql +=" WHERE 1=1 "; // Immer true, aber sinvoll um keine spezialfälle bei der SQL-Anweisungsgenerierung zu haben.
			
			// Um SQLi zu verhindert speichern wir die Werte um sie dann via PreparedStatement Anweisungen zu setzen
			List<String> values = new ArrayList<String>();
			
			for (Map.Entry<String, String> entry : filter.entrySet()) {
				if(entry.getKey().equals("keep")) {
					sql += " AND (l." + entry.getKey() + "=true OR l.postdate < now() - '1 years'::interval) ";
				}
				else if(entry.getKey().equals("title_like")) {
					sql += " AND LOWER(l.title) LIKE LOWER(?) ";
					values.add("%" + entry.getValue() + "%");
				}
				else if(entry.getKey().equals("author_like")) {
					sql += " AND LOWER(l.author) LIKE LOWER(?) ";
					values.add("%" + entry.getValue() + "%");
				}
				else if(entry.getKey().equals("title_or_author_like")) {
					sql += " AND (LOWER(l.author) LIKE LOWER(?) OR LOWER(l.title) LIKE LOWER(?))";
					values.add("%" + entry.getValue() + "%");
					values.add("%" + entry.getValue() + "%");
				}
				else if(entry.getKey().equals("complain")) {
					sql += " AND l.id IN (SELECT DISTINCT(c.literature_id) FROM complain c)";
					// Finde nur Einträge die mindestens eine Beschwere haben
				}
				else {
					sql += " AND l." + entry.getKey() + "::varchar=? ";
					values.add(entry.getValue());
				}
			}
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			for(int i = 0; i < values.size(); i++) {
				pstmt.setString(i+1, values.get(i));
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Literature l = new Literature();
				l.setId(rs.getInt("id"));
				l.setAuthor(rs.getString("author"));
				l.setTitle(rs.getString("title"));
				l.setContent(rs.getString("content"));
				l.setMotivation(rs.getString("motivation"));
				l.setUser_id(rs.getInt("l_user_id"));
				l.setSource(rs.getString("source"));
				l.setPostdate(rs.getString("postdate"));
				l.setFormating(rs.getString("formating"));
				l.setTopic(rs.getString("topic"));
				l.setApplication(rs.getString("application"));
				l.setGenre(rs.getString("genre"));
				l.setAdmincomment(rs.getString("admincomment"));
				l.setStatus(rs.getString("status"));
				l.setKeep(rs.getBoolean("keep"));
				literature.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return literature;
	}

	/*
	 * Löscht Werk mit der entsprechenden id
	 */
	public static boolean remove(int id) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"DELETE FROM literature WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	/*
	 *Findet mit Werk mit entsprechender id
	 */
	public static Literature findById(int id) {
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("id", String.valueOf(id));
		List<Literature> literature = find(filter);
		if(literature.size() == 1)
			return literature.get(0);
		return null;
	}
	
	/*
	 * Aktualisiert Werk. literature.id muss gültig sein.
	 */
	public static boolean update(Literature literature) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"UPDATE literature "
					+ "SET author=?, "
					+ "title=?, "
					+ "content=?, "
					+ "motivation=?, "
					+ "user_id=?, "
					+ "source=?, "
					+ "formating=?, "
					+ "topic=?, "
					+ "application=?, "
					+ "genre=?, "
					+ "admincomment=?, "
					+ "status=?, "
					+ "keep=? "
					+ "WHERE id = ?");
			preparedStatement.setString(1, literature.getAuthor());
			preparedStatement.setString(2, literature.getTitle());
			preparedStatement.setString(3, literature.getContent());
			preparedStatement.setString(4, literature.getMotivation());
			preparedStatement.setInt(5, literature.getUser_id());
			preparedStatement.setString(6, literature.getSource());
			preparedStatement.setString(7, literature.getFormating());
			preparedStatement.setString(8, literature.getTopic());
			preparedStatement.setString(9, literature.getApplication());
			preparedStatement.setString(10, literature.getGenre());
			preparedStatement.setString(11, literature.getAdmincomment());
			preparedStatement.setString(12, literature.getStatus());
			preparedStatement.setBoolean(13, literature.getKeep());
			preparedStatement.setInt(14, literature.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
}
