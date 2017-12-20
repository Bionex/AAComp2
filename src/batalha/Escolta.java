package batalha;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import telas.Tabuleiro;

public class Escolta extends Navio {
	public static final int tamanho = 3;
	public static final int id = 3;
	// retorna verdadeiro se j� tiver um navio nessas posicoes
		public static boolean checaNavio(int[][] vetor, int linha, int coluna) {
			int contador = 0;
			while(contador < Escolta.tamanho){
				if(vetor[linha][coluna+contador] != 0)
					return true;
				contador++;
			}
			return false;
		}
		
		public static int getId(){
			return Escolta.id;
		}
		
		public static void posicionarNavio(int[][] matriz, int linha, int coluna) {
			int contador = 0;
			while (contador < Escolta.tamanho) {
				matriz[linha][coluna+contador] = Escolta.id;
				contador++;
			}	
		}
		
		public static void darTiro(int[][]valor_enemy, JButton[][] barcos_enemy, int linha, int coluna){
			for(int i = 0; i < 2; i++){
				coluna += i;
				if(coluna > 9)
					break;
				if(valor_enemy[linha][coluna] == 0){
					barcos_enemy[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha][coluna].setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha][coluna].setEnabled(false);
					valor_enemy[linha][coluna] = -1;
				}	
				else if(valor_enemy[linha][coluna] != 0 && valor_enemy[linha][coluna] != -1){
				barcos_enemy[linha][coluna]
						.setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Configura a imagem para o Bot�o desabilitado
					barcos_enemy[linha][coluna]
						.setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Desabilita o bot�o j� clicado para n�o continuar
					
					barcos_enemy[linha][coluna].setEnabled(false);
					valor_enemy[linha][coluna] = -1;
				}
			}
				
		}
			
}
