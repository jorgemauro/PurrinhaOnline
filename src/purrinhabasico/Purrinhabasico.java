/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purrinhabasico;

import Cliente.Cliente;
import Servidor.Servico;

/**
 *
 * @author Voidk
 */
public class Purrinhabasico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Servico S= new Servico();
        Cliente C1 = new Cliente();
        Cliente C2 = new Cliente();
        S.run();
        C1.run();
        C2.run();
        
    }
    
}
