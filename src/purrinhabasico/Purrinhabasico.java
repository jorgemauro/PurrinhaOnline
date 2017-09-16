/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purrinhabasico;

/**
 *
 * @author Voidk
 */
public class Purrinhabasico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        jogo j= new jogo(2);
        do
        j.rodada();
        while(j.temVencedor());
        
    }
    
}
