/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Voidk
 */
public class IniciaCliente {
    public static void main(String[] args) throws UnknownHostException,
            IOException {
        new Cliente("127.0.0.1", 12346).executa();
    }
}
