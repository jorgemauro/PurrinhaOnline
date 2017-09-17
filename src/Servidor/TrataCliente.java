/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.InputStream;
import java.util.Scanner;

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
        Scanner s = new Scanner(this.cliente);
        
        s.close();
    }
}
