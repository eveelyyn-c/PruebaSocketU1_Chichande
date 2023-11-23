/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author EVELYN
 */
public class Servidor {
    ServerSocket servidor; 
    Socket cliente; 
    Map<String, String> map;
    public Servidor() {
    }
    
    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }  
    
    public void cargarDiccionario(){
       map = new HashMap<>();
       map.put("A","Azuay");
       map.put("B","Bolivar");
       map.put("U","Canar");
       map.put("X","Cotopaxi");
       map.put("H","Chimborazo");
       map.put("O","El oro");
       map.put("E","Esmeraldas");
       map.put("Q","Francisco de Orellana");
       map.put("W","Galápagos");
       map.put("G","Guayas");
       map.put("I","Imbabura");
       map.put("L","Loja");
       map.put("R","Los Ríos");
       map.put("M","Manabí");
       map.put("V","Morona Santiago");
       map.put("N","Napo");
       map.put("P","Pastaza");
       map.put("P","Pichincha");
       map.put("Y","Santa Elena");
       map.put("J","Santo Domingo de los Tsáchilas");
       map.put("S","Sucumbíos");
       map.put("T","Tungurahua");
       map.put("Z","Zamora Chinchipe");   
              
       
    }
    public void iniciarSocketServidor(int puerto){
    try{
        cargarDiccionario();
        DataInputStream entrada;
        DataOutputStream salida;
        System.out.println("Esperando conexion en el puerto: "+puerto);
        servidor= new ServerSocket(puerto);
        cliente=servidor.accept();
        System.out.println("Cliente conectado: "+cliente.getInetAddress());
        entrada = new DataInputStream(cliente.getInputStream());
        String mensajeCliente = entrada.readUTF();
        System.out.println("Cliente: "+mensajeCliente);
        salida = new DataOutputStream(cliente.getOutputStream());
        
        if (map.containsKey(mensajeCliente)) {
            salida.writeUTF(map.get(mensajeCliente)); //lee SOLO LA PRIMERA LETRA DE LA PLACA
        } else {
            salida.writeUTF("Su solicitud fue registrada, pronto nos comunicaremos!!");
        }
    } catch(Exception e){
        
    }
 }  
    public static void main(String[] args){
        Servidor obj1 = new Servidor();
        obj1.iniciarSocketServidor(100);
    }  
}
