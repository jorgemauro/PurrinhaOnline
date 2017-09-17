/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.IOException;

/**
 *
 * @author Voidk
 */
public class IniciaServico {
    public static void main(String[] args) throws IOException {
        new Servico(12345).executa();
    }
}
