import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) {
        int opcao;
		
		do {
			opcao = Integer.parseInt(showInputDialog(Util.menu()));
			if (opcao < 1 || opcao > 5) {
				showMessageDialog(null, "Opção inválida");			
				} else {
					switch (opcao) {
					case 1: 
						Util.registrarProduto();
					break;
					case 2: 
						Util.pesquisarProduto();
					break;
					case 3: 
						Util.listarProduto();
					break;
					case 4: 
						Util.removerProduto();
					break;
					case 5: 
						Util.exit();
                    break;
					}
				}
		} while(opcao != 6);
    }
}
