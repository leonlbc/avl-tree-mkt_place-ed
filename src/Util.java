import static javax.swing.JOptionPane.showInputDialog;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Util {
    private static ArvoreBinaria<Produto> ab = new ArvoreBinaria<Produto>();
	
	public static String menu(){
		String aux = "Escolha uma opcao: \n";
		aux += "1. Registrar Produto  \n";
		aux += "2. Pesquisar Produto \n";
		aux += "3. Listar Produtos \n";
		aux += "4. Remover um Produto \n";
		aux += "5. Encerrar programa\n";
		return aux;
	}

	public static void registrarProduto() {
		int codigo, estoque;
		String nome;
        double valor;

		codigo = Integer.parseInt(showInputDialog("Codigo do Produto:"));
		nome = showInputDialog("Nome do Produto:");
        valor = Double.parseDouble(showInputDialog("Valor do Produto:"));
        estoque = Integer.parseInt(showInputDialog("Quantidade Em Estoque:"));

		Produto produto = new Produto(codigo, nome, valor, estoque);

        if (ab.pesquisar(produto).isPresent()) {
            JOptionPane.showMessageDialog(null,
                 "Ja existe um produto com esse codigo ja inserido");
        } else {
            ab.inserir(produto);
            JOptionPane.showMessageDialog(null,
                 "Produto cadastrado com sucesso");
        }
	}

	public static void listarProduto() {
        ArrayList<Produto> arrayProdutos = new ArrayList<Produto>();

        arrayProdutos = ab.emOrdem(arrayProdutos);
        String listaProdutos = arrayProdutos.stream().map(Object::toString)
                        .collect(Collectors.joining("\n"));

		JOptionPane.showMessageDialog(null, listaProdutos);
	}

	public static void removerProduto() {
		Produto produto;
		produto = produtoPorCodigo();

		if(ab.pesquisar(produto).isPresent()) {
            ab.deletaDado(produto);
			JOptionPane.showMessageDialog(null, "Produto removido!");
		} else {
			JOptionPane.showMessageDialog(null, "Produto nao Existe!");
		}
	}

    public static void pesquisarProduto() {
        Produto produto;
        produto = produtoPorCodigo();

        String prodEncontr = ab.pesquisar(produto)
            .map(Produto::toString).orElse(
            "Nenhum produto encontrado"
        );
        
        JOptionPane.showMessageDialog(null, prodEncontr);
    }

    public static Produto produtoPorCodigo(){
        int codigo;
        codigo = Integer.parseInt(showInputDialog("codigo"));
		Produto produto = new Produto(codigo);
        return produto;
    }

	public static void exit() {
		System.exit(0);
	}

}
