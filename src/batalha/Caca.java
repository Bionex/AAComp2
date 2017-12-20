package batalha;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import telas.Tabuleiro;

public class Caca extends Navio {
	public static final int tamanho = 2;
	public static final int id = 4;
	
	public static int getId(){
		return Caca.id;
	}
	public static void posicionarNavio(int[][] matriz, int linha, int coluna) {
		int contador = 0;
		while (contador < Caca.tamanho) {
			matriz[linha][coluna+contador] = Caca.id;
			contador++;
		}	
	}
	// retorna verdadeiro se j� tiver um navio nessas posicoes
		public static boolean checaNavio(int[][] vetor, int linha, int coluna) {
			int contador = 0;
			while(contador < Caca.tamanho){
				if(vetor[linha][coluna+contador] != 0)
					return true;
				contador++;
			}
			return false;
		}
		public static void darTiro(int[][]valor_enemy, JButton[][] barcos_enemy, int linha, int coluna){
			for(int l = -1; l <= 1; l++){
				int linha_temp = linha + l;
				if(linha_temp < 0)
					continue;
				else if(linha_temp > 9)
					continue;
				if(valor_enemy[linha_temp][coluna] == 0){
					barcos_enemy[linha_temp][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha_temp][coluna].setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha_temp][coluna].setEnabled(false);
					valor_enemy[linha_temp][coluna] = -1;
				}
				else if(valor_enemy[linha_temp][coluna] != 0 && valor_enemy[linha_temp][coluna] != -1){
					barcos_enemy[linha_temp][coluna]
						.setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Configura a imagem para o Bot�o desabilitado
					barcos_enemy[linha_temp][coluna]
						.setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Desabilita o bot�o j� clicado para n�o continuar
					
					barcos_enemy[linha_temp][coluna].setEnabled(false);
					valor_enemy[linha_temp][coluna] = -1;
				}
			}
			for(int c = -1; c <= 1; c ++){
				int coluna_temp = coluna + c;
				if(coluna_temp < 0)
					continue;
				else if(coluna_temp > 9)
					continue;
				if(valor_enemy[linha][coluna_temp] == 0){
					barcos_enemy[linha][coluna_temp].setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha][coluna_temp].setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/water.png")));
					barcos_enemy[linha][coluna_temp].setEnabled(false);
					valor_enemy[linha][coluna_temp] = -1;
				}
				else if(valor_enemy[linha][coluna_temp] != 0 && valor_enemy[linha][coluna_temp] != -1){
					barcos_enemy[linha][coluna_temp]
						.setIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Configura a imagem para o Bot�o desabilitado
					barcos_enemy[linha][coluna_temp]
						.setDisabledIcon(new ImageIcon(Tabuleiro.class.getResource("/telas/explosao.png")));

					// Desabilita o bot�o j� clicado para n�o continuar
					
					barcos_enemy[linha][coluna_temp].setEnabled(false);
					valor_enemy[linha][coluna_temp] = -1;
				}
			}
		}
}
