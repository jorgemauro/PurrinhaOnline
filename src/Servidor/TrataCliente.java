/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Voidk
 */
public class TrataCliente implements Runnable{
    private InputStream cliente;
    private Servico servidor;
    boolean escolha=true;
    public TrataCliente(InputStream cliente, Servico servidor){
        this.cliente = cliente;
        this.servidor = servidor;
    }
   // do servidor com os clientes
    public void run() {
        if(this.servidor.Cont<this.servidor.MaxPlayers){
        this.servidor.distribuiMensagem("esperando jogadores...");
        try {
            this.servidor.aguardando(this.servidor.Cont);
        } catch (InterruptedException ex) {
        this.servidor.distribuiMensagem("erro...");
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        while(this.servidor.temVencedor()){
        this.servidor.escolha(cliente);
        this.servidor.distribuiMensagem("esperando jogadores...");
            try {
                this.servidor.aguardando(this.servidor.Cont);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        this.servidor.max=0;
        this.servidor.jogadores.forEach((j)->{
                    this.servidor.max+=j.getPalito();
                    this.servidor.soma+=j.getescolha();
                });
            this.servidor.contplayer=0;
            this.servidor.apostas(cliente);
            this.servidor.contplayer=0;
            this.servidor.vencedoresRodada();
        }
    }
}
