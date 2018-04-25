package main.tello;

public class TelloCommand {

    public enum Command {
        TakeOff("takeoff", false, false),
        Land("land", false, false),
        Up("up", true, true),
        Down("down", true, true),
        Left("left", true, true),
        Right("right", true, true),
        Forward("foward", true, true),
        Back("back", true, true),
        RotateClockWise("cw", true, true),
        RotateCounterClockwise("ccw", true, true),
        Flip("flip", true, false),
        SetSpeed("speed", true, true),
        GetSpeed("speed?", false, false),
        GetBattery("battery?", false, false),
        GetTime("time?", false, false);

        public final String command;
        public final boolean requiresParameter;
        public final boolean usesNumericalParameter;

        Command(String command, boolean requiresParameter, boolean usesNumericalParameter) {
            this.command = command;
            this.requiresParameter = requiresParameter;
            this.usesNumericalParameter = usesNumericalParameter;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    public enum Direction {
        Left("l"),
        Right("r"),
        Forward("f"),
        Back("b"),
        BackLeft("bl"),
        BackRight("rb"),
        FrontLeft("fl"),
        FrontRight("fr");

        public final String param;

        Direction(String param) {
            this.param = param;
        }

        @Override
        public String toString() {
            return param;
        }
    }

    private String command;
    private String parameter;

    public TelloCommand(Command command) throws TelloParameterException {
        if (command.requiresParameter) {
            throw new TelloParameterException("Command: " + command.toString() + " Requires a parameter");
        }
        this.command = command.toString();
    }

    public TelloCommand(Command command, Direction direction) throws TelloParameterException {
        if (command != Command.Flip) {
            throw new TelloParameterException("Command: " + command.toString() + " Doesn't use the Direction parameter");
        }
        this.command = command.toString();
        this.parameter = direction.param;
    }

    public TelloCommand(Command command, int value) throws TelloParameterException {
        if (!command.usesNumericalParameter) {
            throw new TelloParameterException("Command: " + command.toString() + " Doesn't use a numerical parameter");
        }
        this.command = command.toString();
        this.parameter = Integer.toString(value);
    }

    public String getCommand() {
        return command;
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        if (parameter == null) {
            return command;
        }
        return command + " " + parameter;
    }
}
