import main.tello.TelloClient;
import main.tello.TelloParameterException;

import java.io.IOException;

public class Takeoff {

    public static void main(String[] args) throws IOException, TelloParameterException {
        TelloClient telloClient = TelloClient.getInstance();
        System.out.println(telloClient.takeOff());
    }
}
