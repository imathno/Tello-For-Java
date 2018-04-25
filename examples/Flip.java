import main.tello.TelloClient;
import main.tello.TelloCommand;
import main.tello.TelloParameterException;

import java.io.IOException;

public class Flip {

    public static void main(String[] args) throws IOException, TelloParameterException {
        TelloClient telloClient = TelloClient.getInstance();
        System.out.println(telloClient.flip(TelloCommand.Direction.FrontLeft));
    }
}
