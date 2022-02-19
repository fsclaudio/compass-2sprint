package avaliacao2.questao9;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void Salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME , DESCRICAO,DESCONTO,DATA) VALUES (?,?,?,?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setDouble(3, produto.getDesconto());
			pstm.setDate(4, Date.valueOf(java.time.LocalDate.now()));

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}
	}	
	
	public List<Produto> listar() throws SQLException  {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM PRODUTO";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet();) {
				while (rst.next()) {
					Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getDouble(4),rst.getDate(5));
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}

	public List<Produto> listebusca(String n) throws SQLException  {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM PRODUTO WHERE NOME LIKE ? ";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
           
			pstm.setString(1,'%'+n+'%' );
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet();) {
				while (rst.next()) {
					Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getDouble(4),rst.getDate(5));
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
	public void delete(Integer id ) throws SQLException {
		String sql = "DELETE FROM PRODUTO WHERE ID = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			pstm.setInt(1, id);
			pstm.execute();
			
			Integer LinhasAfetadas = pstm.getUpdateCount();

			System.out.println("Numero de Registros deletados= " + LinhasAfetadas);

		}
	}


}
