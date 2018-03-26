package elvaan.litap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import elvaan.litap.database.DatabaseConnection;
import elvaan.litap.model.Comment;

/*
 * Schnittstelle für die Datenbankzugriffe für Kommentare (Comment)
 */
public class CommentRepository {
    /*
     * findet alle Kommentare die an ein bestimtes Werk gerichtet sind
     */
	public static List<Comment> findAllForLiterature(int literature_id) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM comments WHERE literature_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, literature_id);
			ResultSet userResultSet = pstmt.executeQuery();
			while(userResultSet.next()) {
				Comment comment = new Comment();
				comment.setId(userResultSet.getInt("id"));
				comment.setUser_id(userResultSet.getInt("user_id"));
				comment.setLiterature_id(userResultSet.getInt("literature_id"));
				comment.setPostdate(userResultSet.getString("postdate"));
				comment.setComment(userResultSet.getString("comment"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
    /*
     * Speichert ein **neuen** Kommentar in der Datenbank
     */
	public static boolean persist(Comment comment) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO comments "
					+ "(literature_id, user_id, comment, postdate) "
					+ "VALUES(?, ?, ?, CURRENT_TIMESTAMP)");
			preparedStatement.setInt(1, comment.getLiterature_id());
			preparedStatement.setInt(2, comment.getUser_id());
			preparedStatement.setString(3, comment.getComment());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
}
