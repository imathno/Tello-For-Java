import im.tello.TelloClient;
import im.tello.TelloCommand;
import im.tello.TelloParameterException;

import java.io.IOException;

public class Takeoff {

    public static void main(String[] args) throws IOException, TelloParameterException {
        TelloClient telloClient = TelloClient.getInstance();
        System.out.println(telloClient.sendCommandToTello(new TelloCommand(TelloCommand.Command.TakeOff, TelloCommand.Direction.Forward)));
    }
}
