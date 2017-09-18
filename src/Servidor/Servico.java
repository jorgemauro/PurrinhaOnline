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
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import purrinhabasico.jogador;
import purrinhabasico.jogo;

/**
 *
 * @author Voidk
 */
public class Servico{
    
    private int porta;
    public int MaxPlayers=2;
    public int Cont=0;
    public List<jogador> jogadores =new ArrayList<>();
    private List<PrintStream> clientes;
    int max;
    int conectados=0;
    int contplayer=0;
    int soma=0;
    int vencedores[];
    boolean terminou=true;
    int vencedorFim;
    public Servico(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }

    public void executa() throws IOException {
        // abertura de porta
        ServerSocket servidor = new ServerSocket(this.porta);

        while (true) {
            // aceita cliente e imprime cliente conectado
            Socket cliente = servidor.accept();

            // adiciona saida do cliente a lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);
            this.jogadores.add(new jogador(this.Cont));
            this.Cont++;
            // cria tratador de cliente numa nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
            
            new Thread(tc).start();
        }
    }

    public void distribuiMensagem(String msg) {
        this.clientes.stream().forEach((cliente) -> {
            cliente.println(msg);
        });
    }
    public void apostas(InputStream c){
        System.out.print(this.contplayer);
        this.clientes.stream().forEach((j)->{
                j.println("jogador "+(this.jogadores.get(this.contplayer).id +1) +" digite sua aposta de 0 a "+this.max);
                int apostando;
                do{
                Scanner s = new Scanner(c);
                apostando=parseInt(s.nextLine());
                }while(apostando<0&&apostando>this.max);
                this.jogadores.get(contplayer).setpalpite(apostando);
                if(apostando==this.soma){
                this.vencedores[contplayer]=1;
                this.jogadores.get(contplayer).menosPalito();
                }
                this.contplayer++;
        });
    }
    public void escolha(InputStream c){
    this.clientes.stream().forEach((j)->{
                j.println("jogador "+ (this.jogadores.get(this.contplayer).id +1) +" digite quantos palitos você mostra de 0 a "+this.jogadores.get(this.contplayer).palitos);
                int escolhido;
                do{
                Scanner s = new Scanner(c);
                escolhido=parseInt(s.nextLine());
                }while(escolhido<0 || escolhido>this.jogadores.get(contplayer).palitos);
                this.jogadores.get(contplayer).setEscolho(escolhido);
                    this.contplayer++;
            
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
        System.out.println(venc);
    }
    
    public void aguardando(int i) throws InterruptedException{
        while(i!=this.MaxPlayers){
        System.out.println("Aguardando");
            new Thread().sleep(500);
        }
    }
    public boolean temVencedor(){
        jogadores.forEach((j)->{
            if(j.getPalito()==0){
                this.terminou=false;
            }
            
        });
       
        return(this.terminou);
    }
    
}
