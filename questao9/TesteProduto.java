package avaliacao2.questao9;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TesteProduto {
	public static void menu() {
		System.out.println("\t===========CFS SYSTEM==========");
		System.out.println();
		System.out.println("\t Digite a opção desejada");
		System.out.println("1. Inserir uma oferta");
		System.out.println("2. Atualizar uma oferta");
		System.out.println("3. Deletar uma oferta");
		System.out.println("4. Listar nome que contem?");
		System.out.println("5. Listar todos produtos");
		System.out.println("0. Sair");
		System.out.println("Opcao:");
	}

	static ArrayList<Produto> products = new ArrayList<Produto>();

	public static ProdutoDAO createProductDaoConn() {
		Connection connection = new ConnectionFactory().createConnection();
		ProdutoDAO produtoDAO = new ProdutoDAO(connection);
		return produtoDAO;
	}

	public static void inclui() throws SQLException {

		Scanner entrada = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		System.out.println("Digite o nome do Produto:");
		String nome = entrada.nextLine();

		System.out.println("Digite a descrição do produto:");
		String desc = entrada.nextLine();

		System.out.println("Digite a valor do produto:");
		String preco = entrada.nextLine();

		double valor = Double.valueOf(preco);
		Produto produto = new Produto(nome, desc, valor);

		createProductDaoConn().Salvar(produto);
		insert3products();

		List<Produto> listaProd = createProductDaoConn().listar();

		listaProd.stream().forEach(lp -> System.out.println(lp));
	}

	public static void altera() throws SQLException {
		Scanner entradaalt = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		System.out.println("Digite o codigo do Produto a ser Alterado:");
		String strid = entradaalt.nextLine();

		System.out.println("Digite o nome do Produto:");
		String nome = entradaalt.nextLine();

		System.out.println("Digite a descrição do produto:");
		String desc = entradaalt.nextLine();

		System.out.println("Digite a valor do produto:");
		String custo = entradaalt.nextLine();

		double valor = Double.valueOf(custo);
		Integer id = Integer.valueOf(strid);
		Produto produto = new Produto(nome, desc, valor);

		if (createProductDaoConn().existId(id)) {
			createProductDaoConn().alterar(produto.getNome(), produto.getDescricao(), produto.getDesconto(), id);
		} else {
			createProductDaoConn().Salvar(produto);
			insert3products();
		}

		List<Produto> listaProd = createProductDaoConn().listar();

		listaProd.stream().forEach(lp -> System.out.println(lp));
	}

	public static void excluir() throws SQLException {
		Scanner entradaexc = new Scanner(System.in);

		System.out.println("Digite o id do Produto:");
		int id = entradaexc.nextInt();

		if (createProductDaoConn().existId(id)) {
			createProductDaoConn().delete(id);
		} else {
			System.out.println("Produto de ID " + id + " Não exite!");
		}
	}

	private static void listaAll() throws SQLException {

		List<Produto> listaProd = createProductDaoConn().listar();

		listaProd.stream().forEach(lp -> System.out.println(lp));
	}

	public static void consulta() throws SQLException {
		Scanner entradacon = new Scanner(System.in);
		Locale.setDefault(Locale.ENGLISH);

		System.out.println("Digite o nome do Produto:");
		String nome = entradacon.nextLine();

		Produto produto = new Produto(nome);

		List<Produto> listaProd = createProductDaoConn().listebusca(produto.getNome());

		listaProd.stream().forEach(lp -> System.out.println(lp));

	}

	public static void insert3products() {
		products.add(new Produto("TV", "Led 32 pol", 160.60));
		products.add(new Produto("TV", "Led 50 pol", 450.60));
		products.add(new Produto("Notebook", "Notebook DEll i7", 440.70));
		products.add(new Produto("TV", "Led 42 pol", 252.60));
		products.add(new Produto("Notebook", "Notebook DEll i5", 650.10));
		products.add(new Produto("Celular", "Motorola 128GB", 335.60));
		products.add(new Produto("Celular", "Sansung 128GB", 150.50));
		products.add(new Produto("Radio", "Radio Rlogioe", 50.60));
		products.add(new Produto("Camera", "Camera Fotografica", 235.60));
		for (int i = 0; i < 3; i++) {
			Collections.shuffle(products);
			Produto produto = new Produto(products.get(i).getNome(), products.get(i).getDescricao(),
					products.get(i).getDesconto());
			createProductDaoConn().Salvar(produto);
		}
	}

	public static void main(String[] args) throws SQLException {

		int opcao;
		Scanner opcaoentrada = new Scanner(System.in);

		do {
			menu();
			opcao = opcaoentrada.nextInt();

			switch (opcao) {
			case 1:
				inclui();
				break;

			case 2:
				altera();
				break;

			case 3:
				excluir();
				break;

			case 4:
				consulta();
				break;

			case 5:
				listaAll();
				break;

			default:
				System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
		opcaoentrada.close();
	}
}
