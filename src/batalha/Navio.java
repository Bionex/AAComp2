package batalha;



public abstract class Navio {
	private static int tamanho;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		Navio.tamanho = tamanho;
	}
	
	
	// retorna verdadeiro se já tiver um navio nessas posicoes
	public static boolean checaNavio(int[][] vetor, int linha, int coluna) {
		int contador = 0;
		while(contador < tamanho){
			if(vetor[linha][coluna+contador] != 0)
				return true;
			contador++;
			System.out.println(tamanho);
		}
		return false;
	}
	
	public void posicionarNavio(int[][] matriz, int linha, int coluna, Navio navio) {
		int contador = 0;
		if (navio instanceof PortaAviao){
			//int id = PortaAvião.getId();
			while (contador < getTamanho()) {
				matriz[linha][coluna+contador] = PortaAviao.id;
				contador++;
			}
		}

		else if(navio instanceof Escolta) {
			//int id = Escolta.getId();
			while (contador < getTamanho()) {
				matriz[linha][coluna+contador] = Escolta.id;
				contador++;
			}
		}
		else if(navio instanceof Caca) {
			//int id = Caca.getId();
			while (contador < getTamanho()) {
				matriz[linha][coluna+contador] = Caca.id;
				contador++;
			}
		}
		
		else if(navio instanceof Submarino) {
			//int id = Submarino.getId();
			while (contador < getTamanho()) {
				matriz[linha][coluna+contador] = Submarino.id;
				contador++;
			}
		}

		
		/*while (contador < getTamanho()) {
			matriz[linha][coluna+contador] = id;
			contador++;
		}*/
	}

}
