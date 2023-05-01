import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {

        DatagramSocket socketServidor = new DatagramSocket(4000);
        System.out.println("SERVIDOR OUVINDO");

        /*
        Recebendo requisição do cliente
         */
        byte[] palavraAReceber = new byte[1024];
        DatagramPacket pacoteAReceber = new DatagramPacket(palavraAReceber, palavraAReceber.length);
        socketServidor.receive(pacoteAReceber);
        String palavraRecebida = new String(pacoteAReceber.getData());
        System.out.println("DO CLIENTE: " + palavraRecebida);

        /*
        Enviando resposta ao cliente
         */
        byte[] palavraAEnviar = new byte[1024];
        String palavraModificada = palavraRecebida.toUpperCase();
        palavraAEnviar = palavraModificada.getBytes();
        InetAddress IPCLiente = pacoteAReceber.getAddress();
        int portaCliente = pacoteAReceber.getPort();

        DatagramPacket pacoteAEnviar = new DatagramPacket(palavraAEnviar, palavraAEnviar.length, IPCLiente, portaCliente);
        socketServidor.send(pacoteAEnviar);

        /*
        Finalizando a conexão
         */
        socketServidor.close();

    }
}


