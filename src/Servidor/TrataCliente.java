/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.InputStream;
import java.io.PrintStream;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge e Rafa
 */
public class TrataCliente implements Runnable {

    private InputStream cliente;
    PrintStream falaCliente;
   private Servico servidor;
    boolean escolha = true;
 
    public TrataCliente(InputStream cliente, Servico servidor, PrintStream falaCliente) {
     this.cliente = cliente;
     this.servidor = servidor;
        this.falaCliente = falaCliente;
   }

    // do servidor com os clientes
    public synchronized void run() {
        // verifica se há um vencedor geral
        this.servidor.distribuiMensagem("aguarde ps proximos jogadores");
        if (this.AllResponses()) {
            try {
                this.servidor.contplayer++;
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.servidor.contplayer++;
            this.notifyAll();
       
        }

        this.servidor.contplayer = 0;
        while (this.servidor.NtemVencedor()) {
            this.servidor.distribuiMensagem("começou a rodada");
            this.servidor.distribuiMensagem("Digite quantos palitos você mostra de 0 a " + this.servidor.jogadores.get((this.servidor.contplayer)).palitos);
            int escolhido;
            do {
     Scanner s = new Scanner(this.cliente);
                escolhido = parseInt(s.nextLine());
            } while (escolhido < 0 || escolhido > this.servidor.jogadores.get(this.servidor.contplayer).palitos);
            this.servidor.jogadores.get(this.servidor.contplayer).setEscolho(escolhido);
            if (this.AllResponses()) {
                this.servidor.contplayer++;
           try {
                    
               this.wait();
           } catch (InterruptedException ex) {
               Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
           }
            } else {
                this.servidor.contplayer++;
           this.notifyAll();

       }

            this.servidor.contplayer = 0;
            this.servidor.max = 0;
            this.servidor.jogadores.forEach((j) -> {
                this.servidor.max += j.getPalito();
                this.servidor.soma += j.getescolha();
            });
            this.servidor.distribuiMensagem("jogador " + this.servidor.contplayer + " digite sua aposta de 0 a " + this.servidor.max);
            int apostando;
                do{
                Scanner s = new Scanner(this.cliente);
                apostando=parseInt(s.nextLine());
                }while(apostando<0&&apostando>this.servidor.max);
                this.servidor.jogadores.get(this.servidor.contplayer).setpalpite(apostando);
                if(apostando==this.servidor.soma){
                this.servidor.vencedores[this.servidor.contplayer]=1;
                this.servidor.jogadores.get(this.servidor.contplayer).menosPalito();
                }
                this.servidor.contplayer++;
   }  
    }

    public boolean AllResponses() {
        this.servidor.distribuiMensagem("cont player" + this.servidor.contplayer + " max" + this.servidor.MaxPlayers);

        return (this.servidor.contplayer != this.servidor.MaxPlayers);
}
}