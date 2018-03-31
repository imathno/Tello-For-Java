package im.tello;

import java.net.*;

public class TelloClient {

    private static TelloClient instance;

    public final String TELLO_IP = "192.168.10.1";
    public final int TELLO_PORT = 8889;

    public String sendCommandToTello(TelloCommand command) {
        return sendStringToTello(command.toString());
    }

    private String sendStringToTello(String str) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] buffer;
            buffer = str.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buffer,
                                                            buffer.length,
                                                            InetAddress.getByName(TELLO_IP),
                                                            TELLO_PORT);
            clientSocket.send(sendPacket);
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(receivedPacket);
            clientSocket.close();
            return new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TelloClient getInstance() {
        if (instance == null) {
            instance = new TelloClient();
            instance.sendStringToTello("command");
        }
        return instance;
    }
}
