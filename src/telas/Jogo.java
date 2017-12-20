package telas;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

import batalha.Caca;
import batalha.Escolta;
import batalha.Navio;
//import batalha.Navio;
import batalha.PortaAviao;
import batalha.Submarino;

public class Jogo extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private int[][] vetor = new int[10][10];
	private JButton[][] barcos_player = new JButton[10][10];
	private JPanel player = new JPanel();
	//private JPanel enemy = new JPanel();
	private JPanel jContentPane = new JPanel();
	private Container cont;
	private JButton iniciar = new JButton("Jogar");
	private int modo;
	private int cont_barco = 1;
	private int barco_atual = 1;
	private JComboBox<String> combo = new JComboBox<String>(new String[]{"Porta Avião", "Submarino", "Navio de Escolta", "Caça"});
	private JLabel legenda = new JLabel("Selecione um Barco para Posicionar:");
	private boolean [] isPlaced = new boolean[4];
	
	public Jogo(int modo){
		this.modo = modo;
		setTitle("Configurar o Tabuleiro");
		
		this.setBounds(0,0, 640, 480);
		// Centraliza a janela. Pode ser usado o janela.setLocation() para colocar onde quiser
		setLocationRelativeTo(null);
		// Configura o maximizar e fixa o tamanho da janela
		setResizable(false);
		// Configura o fechamento da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Configura o Container
		setContentPane(jContentPane);
		cont = getContentPane();
		cont.setLayout(null);

		// Configura o Grid de Botões
		player.setBounds(50, 50, 450, 325);
		player.setLayout(new GridLayout(10, 10, 2, 2));
		
		for(int linha = 0; linha < 10; linha++){
			for(int coluna = 0; coluna < 10; coluna++){
				barcos_player[linha][coluna] = new JButton("");
				barcos_player[linha][coluna].addActionListener(this);
				barcos_player[linha][coluna].setFocusable(false);
				player.add(barcos_player[linha][coluna]);
			}
		}
		
		iniciar.setBounds(400, 390, 100, 60);
		iniciar.setFocusable(false);
		iniciar.addActionListener(this);
		
		//combo.setBounds(260, 20, 100, 20);
		combo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(combo.getSelectedIndex() == 0)
					barco_atual = 1;
				else if(combo.getSelectedIndex() == 1)
					barco_atual = 2;
				else if(combo.getSelectedIndex() == 2)
					barco_atual = 3;
				else if(combo.getSelectedIndex() == 3)
					barco_atual = 4;
			}

		});
		cont.add(combo);
		cont.add(legenda);
		cont.add(player);
		cont.add(iniciar);
		if(modo == 0){
			JOptionPane.showMessageDialog(this, "Selecione onde quer colocar os Barcos");
			legenda.setFont(new Font("Verdana", Font.PLAIN, 12));
			legenda.setBounds(20, 20, 250, 20);
			combo.setBounds(260, 20, 100, 20);
		}
		else if(modo == 1){
			vetor = gerarCampo();
			atualizaTabuleiro(barcos_player, vetor);		
		}
		else if(modo == 2){
			try{
				vetor = gerarCampoArquivo();
			}
			catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(this, "Arquivo não encontrado, Campo gerado aleatoriamente", e.getMessage(), JOptionPane.ERROR_MESSAGE);	
				vetor=gerarCampo();
			}
			finally{
				
				atualizaTabuleiro(barcos_player, vetor);	
			}
		}
		
}
	
	public void atualizaTabuleiro(JButton[][] botao, int[][] vetor){
		for(int linha = 0; linha < 10; linha++){
			for(int coluna = 0; coluna < 10; coluna++){
				if(vetor[linha][coluna] != 0)
					if(vetor[linha][coluna] < 0)
						botao[linha][coluna].setText("X");
					else
						botao[linha][coluna].setText(String.valueOf(vetor[linha][coluna]));
			}
		}
	}
	
	public boolean navioExiste(int[][] matriz, Navio navio) {
		int id = 0;
		
		if (navio instanceof PortaAviao)
			id = PortaAviao.getId();
		
		else if(navio instanceof Escolta) {
			id = Escolta.getId();
		}
		else if(navio instanceof Caca) {
			id = Caca.getId();
		}
		
		else if(navio instanceof Submarino) {
			id = Submarino.getId();
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(matriz[i][j] == id && id != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int[][] gerarCampoArquivo() throws FileNotFoundException{
		int[][] campo = new int[10][10];
		char [] chars;
		int pos1, pos2, qtd;
		String linha;
		PortaAviao porta = new PortaAviao();
		Escolta escolta = new Escolta();
		Caca caca = new Caca();
		Submarino sub = new Submarino();
		File arq = new File("arquivo.txt");
		Random r = new Random();
		
		Scanner sc = new Scanner(arq);
		
		while(sc.hasNext()) {
			linha = sc.nextLine();
			
			System.out.println(linha);
			chars = linha.toCharArray();
			
			if(chars.length < 4)
				continue;
			qtd = (int) chars[0] - 48;
			
			pos1 = (int)chars[2] - 65;
			pos2 = (int)chars[3] - 49;
			if(chars.length >= 5){
				pos2 = ((int)chars[3] - 48) * 10 + (int)chars[4] - 48; 
				pos2 -= 1;
			}

			
			
			System.out.println("qtd :" + qtd + " pos 1 : " + pos1 + " pos2: "+ pos2);
			
			if(pos1 < 0 || pos1 > 9 || pos2 < 0 || pos2 > 9){
				/*JOptionPane.showMessageDialog(this, "Uma das posições é inválida, vamos gerá-la aleatoriamente");
				pos1 = r.nextInt(10);
				pos2=r.nextInt(6);
				if(PortaAvião.checaNavio(campo, pos1, pos2)){
					while(PortaAvião.checaNavio(campo, pos1, pos2)){
						pos1 = r.nextInt(10);
						pos2=r.nextInt(6);
					}
				}*/
				continue;	
			}
				
			
			if(qtd == 2) {
				if(!this.navioExiste(campo, sub)){
					if(!Submarino.checaNavio(campo, pos1, pos2)) {
						Submarino.posicionarNavio(campo, pos1, pos2);
					}
				}
				else if(!this.navioExiste(campo, caca)){
					if(!Caca.checaNavio(campo, pos1, pos2)) {
						Caca.posicionarNavio(campo, pos1, pos2);
					}
				}
					
			}
			if(qtd == 3) {
				if(!this.navioExiste(campo,escolta)){
					if(!Escolta.checaNavio(campo, pos1, pos2)) {
						Escolta.posicionarNavio(campo, pos1, pos2);
					}
				}

			}
			if(qtd == 4) {
				if(!this.navioExiste(campo, porta)){
					if(!PortaAviao.checaNavio(campo,pos1,pos2)) {
						PortaAviao.posicionarNavio(campo, pos1, pos2);
					}
				}

			}
			
			
			
		}
		if(!navioExiste(campo,porta) || !navioExiste(campo, sub) || !navioExiste(campo,escolta) || !navioExiste(campo, caca))
			JOptionPane.showMessageDialog(this, "Uma(s) das posições é inválida(s), vamos gerá-la(s) aleatoriamente");
		if(!navioExiste(campo,porta)){
			
			pos1 = r.nextInt(10);
			pos2=r.nextInt(10);
			if(PortaAviao.checaNavio(campo, pos1, pos2)){
				while(PortaAviao.checaNavio(campo, pos1, pos2)){
					pos1 = r.nextInt(10);
					pos2=r.nextInt(6);
				}
			}
			PortaAviao.posicionarNavio(campo, pos1, pos2);
		}
		if(!navioExiste(campo, sub)){
			
			pos1 = r.nextInt(10);
			pos2=r.nextInt(10);
			if(Submarino.checaNavio(campo, pos1, pos2)){
				while(Submarino.checaNavio(campo, pos1, pos2)){
					pos1 = r.nextInt(10);
					pos2=r.nextInt(10);
				}
			}
			Submarino.posicionarNavio(campo, pos1, pos2);
		}
		if(!navioExiste(campo,escolta)){
			
			pos1 = r.nextInt(10);
			pos2=r.nextInt(10);
			if(Escolta.checaNavio(campo, pos1, pos2)){
				while(Escolta.checaNavio(campo, pos1, pos2)){
					pos1 = r.nextInt(10);
					pos2=r.nextInt(10);
				}
			}
			Escolta.posicionarNavio(campo, pos1, pos2);
		}
		if(!navioExiste(campo,caca)){
			
			pos1 = r.nextInt(10);
			pos2=r.nextInt(6);
			if(Caca.checaNavio(campo, pos1, pos2)){
				while(Caca.checaNavio(campo, pos1, pos2)){
					pos1 = r.nextInt(10);
					pos2=r.nextInt(10);
				}
			}
			Caca.posicionarNavio(campo, pos1, pos2);
		}
		for(int i = 0; i < 10; i ++){
			for(int j = 0; j < 10; j ++){
				System.out.print(campo[i][j]);
			}
			System.out.println();
		}
		arq.deleteOnExit();
		sc.close();		
		return campo;
		
	}
	
	public int[][] gerarCampo(){
		/*PortaAvião porta = new PortaAvião();
		Escolta escolta = new Escolta();
		Caca caca = new Caca();
		Submarino sub = new Submarino(); */
		int [][] matriz = new int[10][10];
		Random r = new Random();
		int aux1,aux2, peca = 1;
		
		while(peca <= 4) {
			try{
			aux1 = r.nextInt(10);
			aux2 = r.nextInt(10);
			if(peca == 1) {
				if(PortaAviao.checaNavio(matriz,aux1,aux2)){
					while(PortaAviao.checaNavio(matriz,aux1,aux2)){
						aux1 = r.nextInt(10);
						aux2 = r.nextInt(10);
					}
				}
				if(!PortaAviao.checaNavio(matriz, aux1, aux2)) {
					PortaAviao.posicionarNavio(matriz, aux1, aux2);
					peca++;
				}
			}
			else if(peca == 2) {
				if(Submarino.checaNavio(matriz,aux1,aux2)){
					while(Submarino.checaNavio(matriz,aux1,aux2)){
						aux1 = r.nextInt(10);
						aux2 = r.nextInt(10);
					}
				}
				if(!Submarino.checaNavio(matriz, aux1, aux2)) {
					Submarino.posicionarNavio(matriz, aux1, aux2);
					peca++;
				}
			}
			else if(peca == 3) {
				if(Escolta.checaNavio(matriz,aux1,aux2)){
					while(Escolta.checaNavio(matriz,aux1,aux2)){
						aux1 = r.nextInt(10);
						aux2 = r.nextInt(10);
					}
				}
				if(!Escolta.checaNavio(matriz, aux1, aux2)) {
					Escolta.posicionarNavio(matriz, aux1, aux2);
					peca++;
				}
			}
			else if(peca == 4) {
				if(Caca.checaNavio(matriz,aux1,aux2)){
					while(Caca.checaNavio(matriz,aux1,aux2)){
						aux1 = r.nextInt(10);
						aux2 = r.nextInt(10);
					}
				}
				if(!Caca.checaNavio(matriz, aux1, aux2)) {
					Caca.posicionarNavio(matriz, aux1, aux2);
					peca++;
				}
			}
			}
			catch(ArrayIndexOutOfBoundsException e){
				continue;
			}
		}
		
		return matriz;
	}


	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == iniciar){
			if(cont_barco > 4 || modo != 0){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {						
						Tabuleiro tabuleiro = new Tabuleiro(vetor);
						tabuleiro.setVisible(true);						
					}
				});	
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(this, "Ainda faltam Barcos", "OK", JOptionPane.INFORMATION_MESSAGE);

		}
		if(this.modo == 0 && cont_barco <= 4){
			for(int linha = 0; linha < 10; linha++){
				for(int coluna = 0; coluna < 10; coluna++){
					int contador = 1;
					if(e.getSource() == barcos_player[linha][coluna]){
						if(barco_atual == PortaAviao.id){
							if(coluna + PortaAviao.tamanho > 10 || PortaAviao.checaNavio(vetor, linha, coluna)){
								JOptionPane.showMessageDialog(this, "Posicao Invalida", "OK", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(!isPlaced[PortaAviao.id - 1]){
								vetor[linha][coluna] = PortaAviao.id;
								while(contador<PortaAviao.tamanho){
									vetor[linha][coluna+contador] = PortaAviao.id;
									contador++;
								}
								cont_barco++;
								JOptionPane.showMessageDialog(this, "Porta Avião Colocado", "OK", JOptionPane.INFORMATION_MESSAGE);
								atualizaTabuleiro(barcos_player, vetor);
								isPlaced[PortaAviao.id - 1] = true;
								combo.removeItemAt(PortaAviao.id-1);
								combo.insertItemAt("---------", PortaAviao.id-1);
							}
								
						}
						else if(barco_atual == Submarino.id){
							if(coluna + Submarino.tamanho > 10 || Submarino.checaNavio(vetor, linha, coluna)){
								JOptionPane.showMessageDialog(this, "Posicao Invalida", "OK", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else if(!isPlaced[Submarino.id - 1]){
								vetor[linha][coluna] = Submarino.id;
								while(contador<Submarino.tamanho){
									vetor[linha][coluna+contador] = Submarino.id;
									contador++;
								}
								cont_barco++;		
								JOptionPane.showMessageDialog(this, "Submarino Colocado", "OK", JOptionPane.INFORMATION_MESSAGE);
								atualizaTabuleiro(barcos_player, vetor);
								isPlaced[Submarino.id - 1] = true;
								combo.removeItemAt(Submarino.id-1);
								combo.insertItemAt("---------", Submarino.id-1);
							}
								
						}
						else if(barco_atual == Escolta.id){
							if(coluna + Escolta.tamanho > 10 || Escolta.checaNavio(vetor, linha, coluna)){
								JOptionPane.showMessageDialog(this, "Posicao Invalida", "OK", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(!isPlaced[Escolta.id - 1]){
								cont_barco++;
								vetor[linha][coluna] = Escolta.id;
								while(contador<Escolta.tamanho){
									vetor[linha][coluna+contador] = Escolta.id;
									contador++;
								}
								JOptionPane.showMessageDialog(this, "Navio de Escolta Colocado", "OK", JOptionPane.INFORMATION_MESSAGE);
								atualizaTabuleiro(barcos_player, vetor);
								isPlaced[Escolta.id - 1] = true;
								combo.removeItemAt(Escolta.id-1);
								combo.insertItemAt("---------", Escolta.id-1);
							}
								
						}
						else if(barco_atual == Caca.id){
							if(coluna + Caca.tamanho > 10 || Caca.checaNavio(vetor, linha, coluna)){
								JOptionPane.showMessageDialog(this, "Posicao Invalida", "OK", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(!isPlaced[Caca.id-1]){
								vetor[linha][coluna] = Caca.id;
								while(contador<Caca.tamanho){
									vetor[linha][coluna+contador] = Caca.id;
									contador++;
								}
								cont_barco++;
								JOptionPane.showMessageDialog(this, "Caça Colocado", "OK", JOptionPane.INFORMATION_MESSAGE);
								atualizaTabuleiro(barcos_player, vetor);
								isPlaced[Caca.id - 1] = true;
								combo.removeItemAt(Caca.id-1);
								combo.insertItemAt("---------",Caca.id - 1);
							}
								
						}
						
					}
				}
			}
		}
	}


}
