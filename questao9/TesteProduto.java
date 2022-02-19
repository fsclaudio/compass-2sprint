package avaliacao2.questao9;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TesteProduto {

	 public static void menu(){
	        System.out.println("\t===========CFS SYSTEM==========");
	        System.out.println();
	        System.out.println("\tDigite a opção desejada");
	        System.out.println("1. Inserir");
	        System.out.println("2. Atualizar");
	        System.out.println("3. Deletar");
	        System.out.println("4. Listar nome que contem?");
	        System.out.println("0. Sair");
	        System.out.println("Opcao:");
	    }

	    static ArrayList<Produto> products = new ArrayList<Produto>();
	    
	    public static void inclui() throws SQLException{

	    	 Scanner entrada = new Scanner(System.in);
	    	 Locale.setDefault(Locale.US);
	    	 
	    	 System.out.println("Digite o nome do Produto:");
	    	 String nome = entrada.nextLine();
	    	 
	    	 System.out.println("Digite a descrição do produto:");
	    	 String desc = entrada.nextLine();
	    	 
	    	 System.out.println("Digite a valor do produto:");
	    	 double valor = entrada.nextDouble();
	    	
	    	Produto produto = new Produto(nome,desc,valor);

			try (Connection connection = new ConnectionFactory().createConnection()) {

				ProdutoDAO produtoDAO = new ProdutoDAO(connection);
				produtoDAO.Salvar(produto);
				insert3products();
				List<Produto> listaProd = produtoDAO.listar();
				
				listaProd.stream().forEach(lp -> System.out.println(lp));
			}
	    }
	    
	    public static void altera(){
	        System.out.println("Você entrou no método Atualizar.");
	    }
	    
	    public static void exclui(){
	        System.out.println("Você entrou no método deletar.");
	    }
	    
	    public static void consulta() throws SQLException{
	    	Scanner entrada = new Scanner(System.in);
	    	 Locale.setDefault(Locale.US);
	    	 
	    	 System.out.println("Digite o nome do Produto:");
	    	 String nome = entrada.nextLine();
	    	
	    	Produto produto = new Produto(nome);

			try (Connection connection = new ConnectionFactory().createConnection()) {

				ProdutoDAO produtoDAO = new ProdutoDAO(connection);
				produtoDAO.listebusca(produto.getNome());
				
				List<Produto> listaProd = produtoDAO.listebusca(produto.getNome());
				
				listaProd.stream().forEach(lp -> System.out.println(lp));
			}
	    }
	    
	    public static  void insert3products(){
	    	products.add(new Produto("TV","Led 32 pol", 160.60));
			products.add(new Produto("TV","Led 50 pol", 450.60));
			products.add(new Produto("Notebook","Notebook DEll i7", 440.70));
			products.add(new Produto("TV","Led 42 pol", 252.60));
			products.add(new Produto("Notebook","Notebook DEll i5", 650.10));
			products.add(new Produto("Celular","Motorola 128GB", 335.60));
			products.add(new Produto("Celular","Sansung 128GB", 150.50));
			products.add(new Produto("Radio","Radio Rlogioe", 50.60));
			products.add(new Produto("Celular","LG 64GB", 235.60));
			for(int i=0;i<3;i++) {
				Collections.shuffle(products);
				Produto produto = new Produto(products.get(i).getNome(),products.get(i).getDescricao(),products.get(i).getDesconto());

				try (Connection connection = new ConnectionFactory().createConnection()) {

					ProdutoDAO produtoDAO = new ProdutoDAO(connection);
					produtoDAO.Salvar(produto);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    }
	   
	public static void main(String[] args) throws SQLException {
		
		int opcao;
        Scanner entrada = new Scanner(System.in);
        
        do{
            menu();
            opcao = entrada.nextInt();
            
            switch(opcao){
            case 1:
                inclui();
                break;
                
            case 2:
                altera();
                break;
                
            case 3:
                exclui();
                break;
                
            case 4:
                consulta();
                break;
            
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
	  
	}

}
