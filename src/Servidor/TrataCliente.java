/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import purrinhabasico.Jogador;

/**
 *
 * @author Voidk
 */
public class TrataCliente implements Runnable {

   private Servico servidor;
    private Socket cliente;
    boolean escolha = true;
 
   public TrataCliente(InputStream cliente, Servico servidor) {
     this.cliente = cliente;
     this.servidor = servidor;
   }
 
    @Override
   public synchronized void run() {
        // verifica se há um vencedor geral
        PrintStream ps = new PrintStream(cliente.getOutputStream());
                ps.println("Esperando outros Jogadores");
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
            this.falaCliente.println("começou a rodada"+this.cliente);
            this.falaCliente.println("Digite quantos palitos você mostra de 0 a " + this.servidor.jogadores.get((this.servidor.contplayer)).palitos);
            int escolhido;
            do {
                Scanner s = null;
                try {
                    s = new Scanner(this.cliente.getInputStream());
                } catch (IOException ex) {
                    Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
     }
                escolhido = parseInt(s.nextLine());
            } while (escolhido < 0 || escolhido > this.servidor.jogadores.get(this.servidor.contplayer).palitos);
            this.servidor.jogadores.get(this.servidor.contplayer).setEscolho(escolhido);
            if (this.AllResponses()) {
                this.servidor.contplayer++;
                try {
                    
            this.servidor.distribuiMensagem("parada");
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
            
        }
    }

    public boolean AllResponses() {
        this.servidor.distribuiMensagem("cont player" + this.servidor.contplayer + " max" + this.servidor.MaxPlayers);

        return (this.servidor.contplayer != this.servidor.MaxPlayers);
    }
}
