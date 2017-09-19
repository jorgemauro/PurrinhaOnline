/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import Cliente.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import purrinhabasico.Jogador;

/**
 *
 * @author Voidk
 */
public class Servico{
    
     private int porta;
    public int MaxPlayers=2;
    public int Cont=0;
    public List<jogador> jogadores =new ArrayList<>();
    private Map<Integer,Socket> clientes;
    int max;
    int conectados=0;
    int contplayer=0;
    int soma=0;
    int vencedores[];
    boolean terminou=true;
    int vencedorFim;
    public Servico(int porta) {
     this.porta = porta;
        this.clientes = new HashMap<Integer,Socket>() {};
   }
   
    public void executa() throws IOException {
        // abertura de porta
     ServerSocket servidor = new ServerSocket(this.porta);
     System.out.println("Porta 12345 aberta!");
     
     while (true) {
            // aceita cliente e imprime cliente conectado
       Socket cliente = servidor.accept();
       System.out.println("Nova conexão com o cliente " +   
         cliente.getInetAddress().getHostAddress()
       );
       
            // adiciona saida do cliente a lista
       PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.put(this.Cont,cliente);
            this.jogadores.add(new jogador(this.Cont));
            this.Cont++;
       // cria tratador de cliente numa nova thread
            Thread thread = new Thread(  new TrataCliente(cliente,this) );
             thread.start();
     }
   }

   public void distribuiMensagem(String msg) {
        this.clientes.forEach((cliente) -> {
            try {
                PrintStream ps = new PrintStream(cliente.getOutputStream());
                ps.println(msg);
            } catch (IOException ex) {
                Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
     }
            
        });
   }
    public void vencedoresRodada(){
        String venc="os vencedores dessa rodada foram jogadores:";
        for(int i=0;i<vencedores.length;i++){
            if(vencedores[i]==1){
            venc+=" "+Integer.toString(this.vencedores[i]+1);
}
            
            this.vencedores[i]=0;
        }
        this.soma=0;
        this.distribuiMensagem(venc);
    }
    
    public void aguardando(int i) throws InterruptedException{
        while(i!=this.MaxPlayers){
        System.out.println("Aguardando");
            new Thread().sleep(500);
        }
    }
    public boolean NtemVencedor(){
        jogadores.forEach((j)->{
            if(j.getPalito()==0){
                this.terminou=false;
            }
            
        });
       
        return(this.terminou);
    }
    
}
