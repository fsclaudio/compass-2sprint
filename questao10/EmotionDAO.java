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
	
	public void Salvar(Emotion emotion) throws SQLException {
		String sql = "INSERT INTO EMOTION (EMOCAO) VALUES (?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, emotion.getEmocao());
			

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					emotion.setId(rst.getInt(1));
				}
			}
		}
	}

	
}
