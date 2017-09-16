/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purrinhabasico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Mauro e Rafael
 */
public class jogo {
     public List<jogador> jogadores =new ArrayList<>();
    int max;
    int conectados=0;
    int contplayer=0;
    int soma=0;
    int vencedores[];
    boolean terminou=true;
    int vencedorFim;
    
    public void addJogador(Socket socket){
        jogadores.add(new jogador(socket));
    }
    /*
    construtor do jogo
    */
   public jogo(int nJ,Socket socket){
        this.max=nJ*3;
        this.vencedores=new int[nJ]; 
        for(int i=0;i<nJ;i++){
            this.vencedores[i]=0;
            this.addJogador(socket);
        }
    }
    /*
    * verificar os jogadores conectados
    *
    */
    public void conecta(){
        this.conectados++;
    }
    public boolean todosconectam(){
        return(this.conectados==jogadores.size());
    }
    public void apostas(){
        jogadores.forEach((j)->{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            this.contplayer++;
            try {
                System.out.println("jogador "+ this.contplayer +" digite sua aposta de 0 a "+this.max);
                int apostando;
                do{
                String entrada = in.readLine();
                apostando=parseInt(entrada);
                }while(apostando<0&&apostando>this.max);
                j.setpalpite(apostando);
                if(apostando==this.soma){
                this.vencedores[contplayer--]=1;
                j.menosPalito();
                }
            } catch (IOException ex) {
                Logger.getLogger(jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void escolha(){
    jogadores.forEach((j)->{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            this.contplayer++;
            try {
                System.out.println("jogador "+ this.contplayer +" digite quantos palitos você mostra de 0 a "+j.palitos);
                int escolhido;
                do{
                String entrada = in.readLine();
                escolhido=parseInt(entrada);
                }while(escolhido<0 || escolhido>j.palitos);
                j.setEscolho(escolhido);
            } catch (IOException ex) {
                Logger.getLogger(jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void vencedoresRodada(){
        String venc="os vencedores dessa rodada foram jogadores:";
        for(int i=0;i<vencedores.length;i++){
            if(vencedores[i]==1){
            venc+=" "+Integer.toString(this.vencedores[i]+1);
            }
            
            this.vencedores[i]=0;
        }
        this.soma=0;
        System.out.println(venc);
    }
    public void rodada(){
        System.out.println("começou outra rodada");
        this.escolha();
        this.max=0;
        jogadores.forEach((j)->{
            this.max+=j.getPalito();
            this.soma+=j.getescolha();
        });
        this.contplayer=0;
        this.apostas();
        this.contplayer=0;
        this.vencedoresRodada();
        
    }
    public boolean temVencedor(){
        jogadores.forEach((j)->{
            if(j.getPalito()==0){
                this.terminou=false;
            }
            
        });
       
        return(terminou);
    }
    
}
