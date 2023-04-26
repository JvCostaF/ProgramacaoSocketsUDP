import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(4000);

        byte[] dadosParaReceber = new byte[1024];
        byte[] dadosParaEnviar = new byte[1024];

        while (true) {
            DatagramPacket pacoteParaReceber = new DatagramPacket(dadosParaReceber, dadosParaReceber.length);

            serverSocket.receive(pacoteParaReceber);

            String frase = new String(pacoteParaReceber.getData());

            InetAddress enderecoIP = pacoteParaReceber.getAddress();

            int porta = pacoteParaReceber.getPort();

            String fraseMaiuscula = frase.toUpperCase();

            dadosParaEnviar = fraseMaiuscula.getBytes();

            DatagramPacket pacoteParaEnviar = new DatagramPacket(dadosParaEnviar, dadosParaEnviar.length, enderecoIP, porta);

            serverSocket.send(pacoteParaEnviar);

        }

    }
}
