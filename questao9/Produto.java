package avaliacao2.questao9;

import java.util.Date;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Double desconto;
	private Date data;
	
	public Produto(Integer id, String nome, String descricao, Double desconto, Date data) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.data = data;
	}
	
	public Produto( String nome, String descricao, Double desconto, Date data) {
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.data = data;
	}
	
	public Produto( String nome, String descricao, Double desconto) {
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
	}
	
	public Produto( String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", desconto=" + desconto
				+ ", data=" + data + "]";
	}
	
}
