package telas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaInicial extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();
	
	private JButton iniciarJogo = new JButton("Iniciar Jogo");
	private JButton fecharJogo = new JButton("Fechar Jogo");
	private JComboBox<String> combo = new JComboBox<String>(new String[]{"Manual", "Aleatorio", "Arquivo"});
	
	
	private JLabel labelBatalhaNaval = new JLabel("Batalha Naval");
	private JLabel alunos = new JLabel("Jogo desenvolvido pelos alunos José Lucas Alves e Andrew Nunes,");
	private JLabel alunos2 = new JLabel("do Curso de Ciencia da Computação da UFRRJ");
	private int modo = 0;
	
	public TelaInicial() {
		//Título da nossa janela
		this.setTitle("Batalha Naval");
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 640, 480);	

		
		// Retira a borda do painel
		contentPane.setBorder(null);
		// Retira o Layout do Painel
		contentPane.setLayout(null);
		// Seta o painel como painel do frame
		setContentPane(contentPane);
		
		// Label Batalha Naval
		labelBatalhaNaval.setFont(new Font("Verdana", Font.BOLD, 30));
		labelBatalhaNaval.setBounds(200, 50, 300, 29);
		contentPane.add(labelBatalhaNaval);
		
		iniciarJogo.setBounds(160, 238, 111, 36);
		iniciarJogo.setFont(new Font("Verdana", Font.PLAIN, 12));		
		iniciarJogo.addActionListener(this);		
		contentPane.add(iniciarJogo);
		
		alunos.setBounds(10, this.getHeight() - 80, this.getWidth(), 30);
		alunos.setFont(new Font("Verdana", Font.ITALIC, 12));
		contentPane.add(alunos);
		alunos2.setBounds(10, alunos.getY()+alunos.getHeight() - 10, this.getWidth(), 30);
		alunos2.setFont(new Font("Verdana", Font.ITALIC, 12));
		contentPane.add(alunos2);
		
		combo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(combo.getSelectedIndex() == 0)
					modo = 0;
				else if(combo.getSelectedIndex() == 1)
					modo = 1;
				else if(combo.getSelectedIndex() == 2)
					modo = 2;
			}

		});
		combo.setBounds(160, 200, 110, 30);
		contentPane.add(combo);
		
		fecharJogo.setBounds(350, 238, 120, 36);
		fecharJogo.setFont(new Font("Verdana", Font.PLAIN, 12));
		fecharJogo.setFocusable(false);		
		fecharJogo.addActionListener(this);		
		contentPane.add(fecharJogo);
		
		// Centralizando a tela quando iniciada
		setLocationRelativeTo(null);
		
		// Ativando a tecla Enter para Iniciar o Jogo
		getRootPane().setDefaultButton(iniciarJogo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == iniciarJogo){
				this.dispose();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {						
						Jogo jogo = new Jogo (modo);
						jogo.setVisible(true);						
					}
				});				
		}			
		
		if (e.getSource() == fecharJogo){
			System.exit(0);
		}

		
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<String> combo) {
		this.combo = combo;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

}
