/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import Servidor.Recebedor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Voidk
 */
public class Cliente {
     private String host;
   private int porta;
   
    public Cliente(String host, int porta) {
     this.host = host;
     this.porta = porta;
   }
   

   public void executa() throws UnknownHostException, IOException {
     Socket cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor: " + this.host);
 
     // thread para receber mensagens do servidor
     Recebedor r = new Recebedor(cliente.getInputStream());
     new Thread(r).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());
     
     while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
     }
     
     saida.close();
     teclado.close();
     cliente.close();    
   }
}
