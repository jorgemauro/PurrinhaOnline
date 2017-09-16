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
public class jogador {
 int palpite;
 int palitos;
 int escolha;
 Socket socket;
 jogador(Socket s){
     this.Conectar(s);
 this.palitos=3;
 }
public  void Conectar(Socket socket){
  this.socket = socket;
 }
 public Socket getSocket(){
     return this.socket;
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
