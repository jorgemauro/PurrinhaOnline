/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Voidk
 */
public class Cliente {
    
    private JFrame frame = new JFrame("Purrinha");
    private JLabel mensagem = new JLabel("");
    //PEGAMOS A PORTA DO SERVIDOR
    private static int porta = 4001;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter	saida;
    
    public Cliente() throws Exception {
        // CONFIGURAÇÃO DO SOCKET PARA CONEXÃO AO SERVIDOR
        this.socket = new Socket("127.0.0.1", porta);
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
        this.mensagem.setBackground(Color.lightGray);
        this.frame.getContentPane().add(mensagem, "South");
        JPanel fundo = new JPanel();
        fundo.setLayout(new GridLayout(3, 3, 5, 5));
        
      
    }
    public void jogar() throws Exception{
        String resposta;
        try{
            resposta = entrada.readLine();
            //Se receber a mensagem de bem vindo do servidor, começamos.
            if (resposta.startsWith("BEM VINDO")){
                
            }
            while (true){
                resposta = entrada.readLine();
                //Após servidor informar que o movimento realizado foi válido, progredimos
                if (resposta.startsWith("VALIDO")){
                }else if (resposta.startsWith("VITORIA")){//Mensagens de vitoria, derrota ou empate
                    mensagem.setText("Você ganhou");  
                    break;
                }else if (resposta.startsWith("DERROTA")){
                    mensagem.setText("Você perdeu");
                    break;
                }else if (resposta.startsWith("EMPATE")){
                    mensagem.setText("Empate");
                    break;
                }else if (resposta.startsWith("MENSAGEM")){
                    mensagem.setText(resposta.substring(9));
                }else{
                    mensagem.setText("Placar: Você: adversário: ");
                }
            }
            saida.println("FECHAR");
        }finally{
            socket.close();
        }
    }
    //Abre pergunta para repetir jogo
    private boolean jogarDenovo(){
        int resposta = JOptionPane.showConfirmDialog(frame, "Deseja jogar novamente?", "Jogo da velha", JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return resposta == JOptionPane.YES_OPTION;
    }
     public  void run() throws Exception{
        while (true){      
            Cliente cliente = new Cliente();
            cliente.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cliente.frame.setSize(300,300);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            cliente.frame.setLocation(dim.width/2- cliente.frame.getSize().width/2, dim.height/2- cliente.frame.getSize().height/2);
            cliente.frame.setVisible(true);
            cliente.frame.setResizable(false);
            cliente.jogar();
            if (!cliente.jogarDenovo()){
                break;
            }
        }
    }
}
