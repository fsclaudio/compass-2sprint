package avaliacao2.questao10;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TestaEmotion {

	public static void main(String[] args) throws SQLException {

		Scanner entrada = new Scanner(System.in);

		Emotion emo = new Emotion();

		try (Connection connection = new ConnectionFactory().createConnection()) {

			EmotionDAO emotionDAO = new EmotionDAO(connection);

			System.out.println("Digite sua frase com ou sem emotions :-) ou :-(");
			String frase = entrada.nextLine();
			emo.selecEmotion(frase);
			emotionDAO.Salvar(emo);
		}

	}

}
