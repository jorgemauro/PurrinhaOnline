/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import Cliente.Cliente;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Voidk
 */
public class Servico{
    
    private int porta;
    private int Cont;
    private List<PrintStream> clientes;
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
            System.out.println("Nova conexão com cliente "
                    + cliente.getInetAddress().getHostAddress());

            // adiciona saida do cliente a lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);
            
            // cria tratador de cliente numa nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
            this.Cont++;
            new Thread(tc).start();
        }
    }

    public void distribuiMensagem(String msg) {
        this.clientes.stream().forEach((cliente) -> {
            cliente.println(msg);
        });
    }
}
