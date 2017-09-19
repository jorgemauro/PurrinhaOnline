package Servidor;

import Cliente.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import purrinhabasico.jogador;
import purrinhabasico.jogo;

/**
 *
 * @author Jorge Mauro e Rafael Tadeu
 */
public class Servico{
    
	private int porta;
    public int MaxPlayers=2;
    public int Cont=0;
    public List<jogador> jogadores;
    private List<PrintStream> clientes;
    int max;
    int conectados=0;
    int contplayer=0;
   int soma=0;
    int vencedores[];
    boolean terminou=true;
    int vencedorFim;
    public Servico(int porta) {
        this.jogadores = new ArrayList<jogador>();
     this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
   }
   
    public void executa() throws IOException {
        // abertura de porta
     ServerSocket servidor = new ServerSocket(this.porta);
     
     while (true) {
            Socket cliente = servidor.accept();
            
       PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);
            this.jogadores.add(new jogador(this.Cont));
			this.Cont++;
            // cria tratador de cliente numa nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), this,ps);
       
            new Thread(tc).start();
     }
   }
 
   public void distribuiMensagem(String msg) {
        this.clientes.stream().forEach((cliente) -> {
            cliente.println(msg);
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
