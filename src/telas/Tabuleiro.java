package telas;


import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import batalha.*;

public class Tabuleiro extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel player = new JPanel();
	private JPanel enemy = new JPanel();
	private JPanel jContentPane = new JPanel();
	private Container cont;
	
	private JLabel dicas_rest = new JLabel("Dicas Restantes: 3");
	private JLabel tiro = new JLabel("Tiro Atual: Submarino");
	private JLabel ajuda_tiro = new JLabel("Acerta 1 espaço");
	
	private JButton[][] barcos_player = new JButton[10][10];
	private JButton[][] barcos_enemy = new JButton[10][10];
	private JButton novo = new JButton("Reiniciar");
	private JButton voltar = new JButton("Voltar");
	private JButton dica = new JButton("Dica");
	private int num_dicas = 3;
	boolean isDica = false;
	private JButton[] tiros = new JButton[4];
	private int valor_player[][] = new int[10][10];
	private int valor_enemy[][] = new int[10][10];
	private int modo_tiro = Caca.id;
	private int[][] pos_jogo = new int[10][10];
	
	private JLabel[] coluna = new JLabel[10];
	private JLabel[] linha = new JLabel[10];
	private JLabel[] coluna_enemy = new JLabel[10];
	private JLabel[] linha_enemy = new JLabel[10];
	private JLabel[] legenda = new JLabel[4];

	
	public Tabuleiro(int[][] vetor){
		for (int linha = 0; linha < 10; linha++){
			for (int coluna = 0; coluna < 10; coluna++){
				pos_jogo[linha][coluna] = vetor[linha][coluna];
			}
		}
		valor_player = vetor.clone();
		// Titulo da janela
		setTitle("Batalha Naval");
		
		this.setBounds(0,0, 1720, 760);
		// Centraliza a janela. Pode ser usado o janela.setLocation() para colocar onde quiser
		setLocationRelativeTo(null);
		// Configura o maximizar e fixa o tamanho da janela
		setResizable(true);
		// Configura o fechamento da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Configura o Container
		setContentPane(jContentPane);
		cont = getContentPane();
		cont.setLayout(null);

		// Configura o Grid de Botões
		player.setBounds(50, 100, 497, 325);
		player.setLayout(new GridLayout(10, 10, 2, 2));
		enemy.setBounds(710, 100, 497, 325);
		enemy.setLayout(new GridLayout(10, 10, 2, 2));

		// Adiciona os Botões no Grid
		for (int linha = 0; linha < 10; linha++){
			for (int coluna = 0; coluna < 10; coluna++){
				barcos_player[linha][coluna] = new JButton("");
				barcos_player[linha][coluna].addActionListener(this);
				barcos_player[linha][coluna].setFocusable(false);
				player.add(barcos_player[linha][coluna]);
			}
		}
		for (int linha = 0; linha < 10; linha++){
			for (int coluna = 0; coluna < 10; coluna++){
				barcos_enemy[linha][coluna] = new JButton("");
				barcos_enemy[linha][coluna].addActionListener(this);
				barcos_enemy[linha][coluna].setFocusable(false);
				enemy.add(barcos_enemy[linha][coluna]);
			}
		}
		atualizaTabuleiro(barcos_player, vetor);
		
		for(int i = 0; i < 4;i++){
			String c = "Porta";
			if(i == 1){
				c = "Sub";
			}
			else if(i == 2){
				c = "Escolta";
			}
			else if(i == 3){
				c = "Caça";
			}
			tiros[i] = new JButton(c);
			tiros[i].setBounds(50+90*i, 435, 70, 50);
			tiros[i].setFocusable(false);
			tiros[i].addActionListener(this);
		}

		// Botão Novo Jogo
		novo.setBounds(7, 14, 96, 35);
		novo.setFocusable(false);
		novo.addActionListener(this);

		// Botão Voltar
		voltar.setBounds(140, 14, 96, 35);
		voltar.setFocusable(false);
		voltar.addActionListener(this);
		
		//Botão Dica
		dica.setBounds(273, 14, 96, 35);
		dica.setFocusable(false);
		dica.addActionListener(this);
		
		dicas_rest.setFont(new Font("Verdana", Font.PLAIN, 12));
		dicas_rest.setBounds(379, 14, 200, 35);

		// Gerando as letras e numeros das colunas
		for(int i = 0; i < 10; i++){
			Integer ii = i+1;
			String num = ii.toString();
			linha[i] = new JLabel(num);
			linha_enemy[i] = new JLabel(num);
			char letra = 'A';
			letra += i;
			coluna[i] = new JLabel(String.valueOf(letra));
			coluna_enemy[i] = new JLabel(String.valueOf(letra));
		}
		// Legenda
		for(int i = 0; i < 4;i++){
			legenda[i] = new JLabel("");
			if(i == 0)
				legenda[i].setText("Legenda: 1 = Porta Aviões");
			else if(i == 1)
				legenda[i].setText("2 = Submarino");
			else if(i == 2)
				legenda[i].setText("3 = Navio de Escolta");
			else if(i == 3)
				legenda[i].setText("4 = Caça");
			legenda[i].setFont(new Font("Verdana", Font.BOLD, 12));
			legenda[i].setBounds(20, 500+30*i , 200, 30);
		}
		
		// Arrumando as letras e numeros no Grid
		for(int i = 0; i < 10; i++){
			linha[i].setBounds(player.getX()+ 10 + 50*i, 80, 50, 20);
			coluna[i].setBounds(20, player.getY() + 32*i, 20, 32);
			
			linha_enemy[i].setBounds(enemy.getX()+10+50*i, 80, 50, 20);
			coluna_enemy[i].setBounds(enemy.getX() - 20, player.getY() + 32*i, 20, 32);
		}
		
		//Aviso de tiro atual
		tiro.setBounds(250, 500, 200, 30);
		tiro.setFont(new Font("Verdana", Font.ITALIC, 12));
		cont.add(tiro);
		ajuda_tiro.setBounds(250, 530, 300, 30);
		ajuda_tiro.setFont(new Font("Verdana", Font.ITALIC, 12));
		cont.add(ajuda_tiro);
		// Adicionando os elementos no Container
		for(JButton tiro : tiros){
			cont.add(tiro);
		}
		for(JLabel legend: legenda){
			cont.add(legend);
		}
		cont.add(player);
		cont.add(enemy);
		cont.add(novo);
		cont.add(voltar);
		cont.add(dica);
		cont.add(dicas_rest);
		for(int i = 0; i < 10; i++){
			cont.add(linha[i]);
			cont.add(coluna[i]);
			cont.add(linha_enemy[i]);
			cont.add(coluna_enemy[i]);
		}
		
		valor_enemy = gerarCampo();
		//atualizaTabuleiro(barcos_enemy, valor_enemy);

	}	
	
	public void atualizaTabuleiro(JButton[][] botao, int[][] vetor){
		for(int linha = 0; linha < 10; linha++){
			for(int coluna = 0; coluna < 10; coluna++){
				if(vetor[linha][coluna] != 0)
					if(vetor[linha][coluna] < 0)
						botao[linha][coluna].setText("");
					else
						botao[linha][coluna].setText(String.valueOf(vetor[linha][coluna]));
			}
		}
	}
	
	public void tiroBot(JButton[][] botao, int[][] tabuleiro, int[][] enemy){
		Random rand = new Random();
		int linha = rand.nextInt(10);
		int coluna = rand.nextInt(10);
		Navio escolta= new Escolta();
		Navio caca = new Caca();
		if(tabuleiro[linha][coluna] == -1){
			while(tabuleiro[linha][coluna] == -1){
				linha = rand.nextInt(10);
				coluna = rand.nextInt(10);
			}
		}
		if(navioExiste(enemy, caca)){
			Caca.darTiro(tabuleiro, botao, linha, coluna);
		}
		else if(navioExiste(enemy, escolta)){
			Escolta.darTiro(tabuleiro, botao, linha, coluna);
		}
		else
			Submarino.darTiro(tabuleiro, botao, linha, coluna);
		
		atualizaTabuleiro(botao, tabuleiro);
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
	
	public void getDica(int [][] enemy, int l, int c){
		for(int linha = 0; linha < 10; linha++){
			if(enemy[linha][c] != 0){
				JOptionPane.showMessageDialog(this, "Tem um barco nessa linha/coluna", "Dica", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		for(int coluna = 0; coluna < 10; coluna++){
			if(enemy[l][coluna] != 0){
				JOptionPane.showMessageDialog(this, "Tem um barco nessa linha/coluna", "Dica", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "Não tem nada", "Dica", JOptionPane.OK_OPTION);
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
	
	public void removeTiros(JButton[][] barcos_player, int[][] valor_player){

		Navio escolta= new Escolta();
		Navio sub = new Submarino();
		Navio porta = new PortaAviao();
		Navio caca = new Caca();
		boolean morreu = true;
		boolean morreu2=  true;
		if(!this.navioExiste(valor_player, escolta)){
			tiros[Escolta.id-1].setEnabled(false);
			if(morreu){
				if(this.navioExiste(valor_player, caca)){
					modo_tiro = Caca.id;
				}
				else
					modo_tiro = Submarino.id;
				morreu = false;
			}
		}
		if(!this.navioExiste(valor_player, sub)){
			tiros[Submarino.id-1].setEnabled(false);
		}
		if(!this.navioExiste(valor_player, porta)){
			tiros[PortaAviao.id-1].setEnabled(false);
		}
		if(!this.navioExiste(valor_player, caca)){
			tiros[Caca.id-1].setEnabled(false);
			if(morreu2){
				if(this.navioExiste(valor_player, escolta)){
					modo_tiro = Escolta.id;
				}
				else
					modo_tiro = Submarino.id;
				morreu2 = false;
			}
			
		}
		atualizaMsgTiro(modo_tiro);
	}
	
	public boolean campoVazio(int[][] matriz){
		Escolta escolta = new Escolta();
		Submarino sub = new Submarino();
		PortaAviao porta = new PortaAviao();
		Caca caca = new Caca();
		if(!this.navioExiste(matriz, escolta) && !this.navioExiste(matriz, sub) && !this.navioExiste(matriz, porta) && !this.navioExiste(matriz, caca))
			return true;
		else
			return false;
	}
	public void atualizaMsgTiro(int modo){
		if(modo == Submarino.id){
			this.tiro.setText("Tiro Atual: Submarino");
			this.ajuda_tiro.setText("Acerta 1 espaço");
		}
		else if(modo == Escolta.id){
			this.tiro.setText("Tiro Atual: Escolta");
			this.ajuda_tiro.setText("Acerta o espaço escolhido e o seu à direita");
		}
		else if(modo == Caca.id){
			this.tiro.setText("Tiro Atual: Caça");
			this.ajuda_tiro.setText("Acerta o espaço escolhido e os 4 adjacentes");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == voltar){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {						
					TelaInicial endgame = new TelaInicial();
					endgame.setVisible(true);						
				}
			});	
		}
		for(int linha = 0; linha< 10; linha++){
			for(int coluna = 0; coluna < 10; coluna++){
				if(e.getSource() == barcos_enemy[linha][coluna]){
					if(!isDica){
							if(modo_tiro == Escolta.id)
								Escolta.darTiro(valor_enemy, barcos_enemy, linha, coluna);
							else if(modo_tiro == Submarino.id)
								Submarino.darTiro(valor_enemy, barcos_enemy, linha, coluna);
							else if(modo_tiro == Caca.id)
								Caca.darTiro(valor_enemy, barcos_enemy, linha, coluna);
							tiroBot(barcos_player, valor_player, valor_enemy);
							removeTiros(barcos_player, valor_player);
							if(campoVazio(valor_player)){
								this.dispose();
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {						
										Posjogo endgame = new Posjogo(false, pos_jogo);
										endgame.setVisible(true);						
									}
								});	
							}	
							else if(campoVazio(valor_enemy)){
								this.dispose();
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {						
										Posjogo endgame = new Posjogo(true, pos_jogo);
										endgame.setVisible(true);						
									}
								});	
							}
							
					}	
					else if(isDica){
						getDica(valor_enemy, linha, coluna);
						isDica = false;				
					}
				}
					
			}
			
		}
		if(e.getSource() == dica){
			if(num_dicas != 0 && isDica == false){
				isDica = true;
				JOptionPane.showMessageDialog(this, "Selecione uma area do oponente", "Dica", JOptionPane.INFORMATION_MESSAGE);
				num_dicas--;
				dicas_rest.setText("Dicas Restantes: " + num_dicas);
			}
			if(num_dicas == 0){
				dica.setEnabled(false);
				dicas_rest.setText("Não Há Dicas Restantes");	
			}
		}
		for(int i = 0; i < 4; i++){
			if(e.getSource() == tiros[i] && i != 0){
				modo_tiro = i+1; // Colocando o modo de tiro para o navio clicado
				atualizaMsgTiro(modo_tiro);
			}
			else if(e.getSource() == tiros[i] && i == 0){
				JOptionPane.showMessageDialog(this, "O Porta Aviões Não dá tiro", "Dica", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(e.getSource() == novo){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {						
					Tabuleiro endgame = new Tabuleiro(pos_jogo);
					endgame.setVisible(true);						
				}
			});	
		}
	}

}
