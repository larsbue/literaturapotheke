package elvaan.litap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import elvaan.litap.database.DatabaseConnection;
import elvaan.litap.model.Complain;

/*
 * Schnittstelle für die Datenbankzugriffe für Beschwerden (Complain)
 */
public class ComplainRepository {

    /*
     * Speichert eine **neue** Beschwerden in der Datenbank
     */
	public static boolean persist(Complain complain) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO complain "
					+ "(literature_id, user_id, complain, status, reason, source, complaindate) "
					+ "VALUES(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
			preparedStatement.setInt(1, complain.getLiterature_id());
			preparedStatement.setInt(2, complain.getUser_id());
			preparedStatement.setString(3, complain.getComplain());
			preparedStatement.setString(4, complain.getStatus());
			preparedStatement.setString(5, complain.getReason());
			preparedStatement.setString(6, complain.getSource());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
    /*
     * Findet alle Beschwerden für ein bestimmtes Werk
     */
	public static List<Complain> findAllForLiterature(int literature_id) {
		List<Complain> complains = new ArrayList<Complain>();
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM complain WHERE literature_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, literature_id);
			ResultSet userResultSet = pstmt.executeQuery();
			while(userResultSet.next()) {
				Complain comment = new Complain();
				comment.setId(userResultSet.getInt("id"));
				comment.setUser_id(userResultSet.getInt("user_id"));
				comment.setLiterature_id(userResultSet.getInt("literature_id"));
				comment.setComplain(userResultSet.getString("complain"));
				complains.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return complains;
	}

}
