
package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author EVELYN
 */
public class Cliente {
    int puerto;
    Socket cliente;
    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));


    public Cliente() {
    }

    public Cliente(int puerto) {
        this.puerto = puerto;
    }
    
    public Cliente(int puerto, Socket cliente) {
        this.puerto = puerto;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
    
    public void iniciarSocketCliente(){
        try {
            String resp="";
                DataOutputStream salida;
                DataInputStream entrada;
                this.cliente=new Socket("127.0.0.1", puerto); 
                salida= new DataOutputStream(this.cliente.getOutputStream());
                String texto = teclado.readLine();
                salida.writeUTF(texto);
                entrada= new DataInputStream(this.cliente.getInputStream());
                System.out.println("Servidor: \n La placa es de: "+entrada.readUTF());
            
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args){
        Cliente obj1 = new Cliente(100);
        obj1.iniciarSocketCliente();
    }
}
