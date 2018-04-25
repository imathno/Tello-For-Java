package main.tello;

import java.io.IOException;
import java.net.*;

public class TelloClient {

    private static TelloClient instance;

    public final String TELLO_IP = "192.168.10.1";
    public final int TELLO_PORT = 8889;

    public String takeOff() throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.TakeOff));
    }

    public String land() throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Land));
    }

    public String up(int height) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Up, height));
    }

    public String down(int height) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Down, height));
    }

    public String left(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Left, value));
    }

    public String right(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Right, value));
    }

    public String forward(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Forward, value));
    }

    public String back(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Back, value));
    }

    public String rotateClockwise(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.RotateClockWise, value));
    }

    public String rotateCouterClockwise(int value) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.RotateCounterClockwise, value));
    }

    public String flip(TelloCommand.Direction direction) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.Flip, direction));
    }

    public String setSpeed(int speed) throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.SetSpeed, speed));
    }

    public String getSpeed() throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.GetSpeed));
    }

    public String getBattery() throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.GetBattery));
    }

    public String getTime() throws TelloParameterException, IOException {
        return sendCommandToTello(new TelloCommand(TelloCommand.Command.GetTime));
    }

    public String sendCommandToTello(TelloCommand command) throws IOException {
        return sendStringToTello(command.toString());
    }

    private String sendStringToTello(String str) throws IOException {
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
    }

    public static TelloClient getInstance() throws IOException {
        if (instance == null) {
            instance = new TelloClient();
            instance.sendStringToTello("command");
        }
        return instance;
    }
}
