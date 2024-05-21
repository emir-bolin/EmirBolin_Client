import java.net.*;
import java.io.*;

public class Client {

    public static void main(String args[]) throws Exception {
        // Default port number we are going to use for sending messages
        int sendPort = 8002;
        // Default port number we are going to use for receiving responses
        int receivePort = 8003;

        // Create a MulticastSocket for sending messages
        MulticastSocket chatSendSocket = new MulticastSocket();

        // Create a DatagramSocket for receiving responses
        DatagramSocket chatReceiveSocket = new DatagramSocket(receivePort);
        System.out.println("Listening for responses on port " + receivePort);

        // Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";

        // Loop for sending messages
        while (true) {
            System.out.print("Type a message for the server (or type 'exit' to quit): ");
            msg = br.readLine();

            if (msg.equalsIgnoreCase("exit")) {
                break;
            }

            // Send the message to Multicast address
            DatagramPacket data = new DatagramPacket(msg.getBytes(), msg.length(), group, sendPort);
            chatSendSocket.send(data);
            System.out.println("Message sent to server: " + msg);

            // Prepare to receive the result from the server
            byte[] buf = new byte[1024];
            DatagramPacket resultPacket = new DatagramPacket(buf, buf.length);
            chatReceiveSocket.receive(resultPacket);
            String result = new String(resultPacket.getData()).trim();
            System.out.println("Received result from server: " + result);
        }

        // Close the sockets
        chatSendSocket.close();
        chatReceiveSocket.close();
    }
}