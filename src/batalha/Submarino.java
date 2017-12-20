package batalha;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import telas.Tabuleiro;

public class Submarino extends Navio {
	public static final int tamanho = 2;
	public static final int id = 2;
	
	public static int getId(){
		return Submarino.id;
	}
	// retorna verdadeiro se já tiver um navio nessas posicoes
		public static boolean checaNavio(int[][] vetor, int linha, int coluna) {
			int contador = 0;
			while(contador < Submarino.tamanho){
				if(vetor[linha][coluna+contador] != 0)
					return true;
				contador++;
			}
			return false;
		}
		
		public static void posicionarNavio(int[][] matriz, int linha, int coluna) {
			int contador = 0;
			while (contador < Submarino.tamanho) {
				matriz[linha][coluna+contador] = Submarino.id;
				contador++;
			}	
		}
		public static void darTiro(int[][]valor_enemy, JButton[][] barcos_enemy, int linha, int coluna){
			if(valor_enemy[linha][coluna] == 0){
				barcos_enemy[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
				barcos_enemy[linha][coluna].setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
				barcos_enemy[linha][coluna].setEnabled(false);
				valor_enemy[linha][coluna] = -1;
			}
			else if(valor_enemy[linha][coluna] != 0 && valor_enemy[linha][coluna] != -1){
				barcos_enemy[linha][coluna]
					.setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

				// Configura a imagem para o Botão desabilitado
				barcos_enemy[linha][coluna]
					.setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

				// Desabilita o botão já clicado para não continuar
				
				barcos_enemy[linha][coluna].setEnabled(false);
				valor_enemy[linha][coluna] = -1;
			}
			
		}
}
