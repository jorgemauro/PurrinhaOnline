/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.IOException;
import purrinhabasico.jogo;
import java.net.ServerSocket;

/**
 *
 * @author Voidk
 */
public class Servico{
    public void run() throws IOException{
     int porta = Integer.parseInt("4001");
        ServerSocket listener = new ServerSocket(porta);
        try{
            while (true){
                //Criado um novo jogo
                jogo J=new jogo(2,listener.accept());
            }
        }finally{
            listener.close();
        }
    }
}
