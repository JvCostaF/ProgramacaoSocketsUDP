import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.HashMap;

public class UDPServer {
    public static void main(String[] args) throws IOException {

        DatagramSocket socketServidor = new DatagramSocket(4000);
        System.out.println("SERVIDOR OUVINDO");

        /*
        Criando Map com 10 palavras (Mini dicionário)
         */
        Map<String, String> dicionario = new HashMap<String, String>();
        dicionario.put("CLIENTE", new String("client"));
        dicionario.put("SERVIDOR", new String("server"));
        dicionario.put("PACOTE", new String("package"));
        dicionario.put("HOSPEDEIRO", new String("host"));
        dicionario.put("PROTOCOLO", new String("protocol"));
        dicionario.put("ROTEADOR", new String("router"));
        dicionario.put("ENLACE", new String("link"));
        dicionario.put("PROVEDOR", new String("provider"));
        dicionario.put("SOQUETE", new String("socket"));
        dicionario.put("REDE", new String("network"));

        /*
        Recebendo requisição do cliente
         */
        byte[] palavraAReceber = new byte[1024];
        DatagramPacket pacoteAReceber = new DatagramPacket(palavraAReceber, palavraAReceber.length);
        socketServidor.receive(pacoteAReceber);

        /*
        Verificando os bytes não nulos
         */
        int count = 0;
        for(byte b: pacoteAReceber.getData()){
            if (b!=0){
                count++;
            }
        }
        byte[] bytesValidos = new byte[count];
        int i = 0;
        for (byte b: pacoteAReceber.getData()){
            if (b != 0){
                bytesValidos[i] = b;
                i++;
            }
        }

        InputStream palavra = new ByteArrayInputStream(pacoteAReceber.getData());
        BufferedReader palavra2 = new BufferedReader(new InputStreamReader(palavra));

        String palavraRecebida = new String(bytesValidos, Charset.forName("UTF-8")).toUpperCase();

        System.out.println("DO CLIENTE: " + palavraRecebida);

        /*
        Enviando resposta ao cliente
         */
        byte[] palavraAEnviar = new byte[palavraRecebida.length()];
        String palavraTraduzida;
        if(dicionario.containsKey(palavraRecebida)){
            palavraTraduzida = dicionario.get(palavraRecebida.toUpperCase());
            palavraAEnviar = palavraTraduzida.getBytes();
        }else{
            palavraTraduzida = "Palavra não encontrada";
            palavraAEnviar = palavraTraduzida.getBytes();
        }
        //palavraAEnviar = palavraTraduzida.getBytes();
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


