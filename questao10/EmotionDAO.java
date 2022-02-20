package avaliacao2.questao10;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmotionDAO {

	private Connection connection;

	public EmotionDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void Salvar(Emotion emotion)  {
		String sql = "INSERT INTO EMOTION (EMOCAO,DATA) VALUES (?,?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, emotion.getEmocao());
			pstm.setDate(2, Date.valueOf(java.time.LocalDate.now()));
			

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					emotion.setId(rst.getInt(1));
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	
}
