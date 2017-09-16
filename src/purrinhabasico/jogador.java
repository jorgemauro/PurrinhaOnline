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
public class jogador {
 int palpite;
 int palitos;
 int escolha;
 jogador(){
 this.palitos=3;
 }
 
 void setpalpite(int p){
     this.palpite=p;
 }
 int getPalpite(){
     return(this.palpite);
 }
 void menosPalito(){
    this.palitos--; 
 }
 int getPalito(){
     return(this.palitos);
 }
 void setEscolho(int e){
     this.escolha=e;
 }
 int getescolha(){
 return this.escolha;
         }
}
