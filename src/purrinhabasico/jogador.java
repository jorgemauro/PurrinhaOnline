/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purrinhabasico;

import java.net.Socket;

import java.io.IOException;
/**
 *
 * @author Voidk
 */
public class jogador{
 public int palpite;
 public int palitos;
 public int escolha;
 public int id;
 public jogador(int id){
     this.id=id;
 this.palitos=3;
 }
 public void setpalpite(int p){
     this.palpite=p;
 }
 public int getPalpite(){
     return(this.palpite);
 }
 public void menosPalito(){
    this.palitos--; 
 }
 public int getPalito(){
     return(this.palitos);
 }
 public void setEscolho(int e){
     this.escolha=e;
 }
 public int getescolha(){
 return this.escolha;
         }
}
