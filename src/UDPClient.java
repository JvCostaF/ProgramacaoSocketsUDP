import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) throws IOException {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket socketCliente = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] dadosParaEnviar = new byte[1024];
        byte[] dadosParaReceber = new byte[1024];

        String frase = inFromUser.readLine();

        DatagramPacket pacoteParaEnviar = (new DatagramPacket(dadosParaEnviar, dadosParaEnviar.length, IPAddress,4000));

        socketCliente.send(pacoteParaEnviar);

        DatagramPacket pacoteRecebido = new DatagramPacket(dadosParaReceber,dadosParaReceber.length);

        socketCliente.receive(pacoteRecebido);

        String fraseModificada = new String(pacoteRecebido.getData());

        System.out.println("FROM SERVER: " + fraseModificada);

        socketCliente.close();
    }
}
