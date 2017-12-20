package telas;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Posjogo extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = new JPanel();
	private JButton fechar = new JButton("Fechar Jogo");
	private JButton reiniciar = new JButton("Reiniciar Jogo");
	private JButton novo = new JButton("Novo Jogo");
	private JLabel win = new JLabel();
	private Container cont;
	private int [][] valor_player = new int[10][10];
	
	public Posjogo(boolean ganhou, int[][] campo){
		valor_player = campo.clone();
		setTitle("Fim de Jogo");
		// Define o tamanho da janela. Pode ser usado o janela.pack() para auto ajuste
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
		
		novo.setBounds(110, 250, 120, 50);
		novo.addActionListener(this);
		reiniciar.setBounds(250, 250, 120, 50);
		reiniciar.addActionListener(this);
		fechar.setBounds(390, 250, 120, 50);
		fechar.addActionListener(this);
		
		win.setFont(new Font("Verdana", Font.BOLD, 30));
		win.setBounds(200, 20, 500, 300);
		if(ganhou){
			win.setText("Você Ganhou!!");
		}
		else if(!ganhou){
			win.setText("Você Perdeu :(");
		}
		
		cont.add(novo);
		cont.add(reiniciar);
		cont.add(fechar);
		cont.add(win);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == novo){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {						
					TelaInicial endgame = new TelaInicial();
					endgame.setVisible(true);						
				}
			});	
		}
		if(e.getSource() == fechar){
			System.exit(0);
		}
		if(e.getSource() == reiniciar){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {						
					Tabuleiro endgame = new Tabuleiro(valor_player);
					endgame.setVisible(true);						
				}
			});	
		}
		
	}
	

}
