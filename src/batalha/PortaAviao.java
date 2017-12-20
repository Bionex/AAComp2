package batalha;

public class PortaAviao extends Navio {
	public static final int tamanho = 4;
	public static final int id = 1;
	// retorna verdadeiro se já tiver um navio nessas posicoes
		public static boolean checaNavio(int[][] vetor, int linha, int coluna) {
			int contador = 0;
			while(contador < PortaAviao.tamanho){
				if(vetor[linha][coluna+contador] != 0)
					return true;
				contador++;
			}
			return false;
		}
		
		public static int getId(){
			return PortaAviao.id;
		}
		
		public static void posicionarNavio(int[][] matriz, int linha, int coluna) {
			int contador = 0;
			while (contador < PortaAviao.tamanho) {
				matriz[linha][coluna+contador] = PortaAviao.id;
				contador++;
			}	
		}
}
