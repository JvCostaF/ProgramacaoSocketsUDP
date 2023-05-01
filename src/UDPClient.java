import java.io.IOException;
import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {

        /*
        Inicializando o socketCliente do Cliente
         */
        DatagramSocket socketCliente = new DatagramSocket();

        /*
        Recebendo dados do usuário
         */
        System.out.println("Digite uma palavra: ");
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();

        /*
        Conectando-se ao servidor e enviando a palavra digitada pelo usuário
         */
        byte[] palavraAEnviar = new  byte[1024];
        palavraAEnviar = palavra.getBytes();
        InetAddress IP = InetAddress.getByName("localhost");
        DatagramPacket pacoteAEnviar = new DatagramPacket(palavraAEnviar,palavraAEnviar.length, IP, 4000);
        socketCliente.send(pacoteAEnviar);

        /*
        Recebendo a resposta do Servidor
         */
        byte[] palavraAReceber = new byte[1024];
        DatagramPacket pacoteAReceber = new DatagramPacket(palavraAReceber, palavraAReceber.length);
        socketCliente.receive(pacoteAReceber);
        String palavraRecebida = new String(pacoteAReceber.getData());
        System.out.println("DO SERVIDOR: " + palavraRecebida);

        /*
        Finalizando a conexão
         */
        socketCliente.close();

    }
}
