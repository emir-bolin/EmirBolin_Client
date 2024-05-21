import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {

        // Default port number we are going to use
        int portnumber = 8001;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);

        // Determine the Ip Address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");
        System.out.println(group);

        // Joins a multicast group
        chatMulticastSocket.joinGroup(group);

        // Prompt a user to enter a message
        String msg = "";
        System.out.println("Type a message for the server: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        // Send the message to Multicast address
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portnumber);
        chatMulticastSocket.send(data);

        // Close the socket
        chatMulticastSocket.close();
    }
}