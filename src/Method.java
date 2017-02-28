import com.sun.istack.internal.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class Method {

    private long time;
    private Boolean isEntry;
    private String methodName;
    private Integer methodId;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss,SSS");

     Method(@NotNull String line) throws InvalidFormatLineException {
        final String SPLIT = " ";

        String[] separate = line.split(SPLIT);
        initDateAndTime(separate[0]);
        initStatus(separate[3]);
        initNameAndID(separate[5]);
    }

    private void initDateAndTime(@NotNull String dateAndTime) throws InvalidFormatLineException {
        try {
            time = simpleDateFormat.parse(dateAndTime).getTime();
        } catch (ParseException e) {
            throw new InvalidFormatLineException();
        }
    }

    private void initStatus(@NotNull String status) throws InvalidFormatLineException {
        final String ENTRY = "entry";
        final String EXIT = "exit";

        switch (status) {
            case ENTRY:
                isEntry = true;
                break;
            case EXIT:
                isEntry = false;
                break;
            default:
                throw new InvalidFormatLineException();
        }
    }

    private void initNameAndID(@NotNull String nameAndID) throws InvalidFormatLineException {
        try {
            nameAndID = nameAndID.substring(1, nameAndID.length() - 1);
            String[] separate = nameAndID.split(":");
            methodName = separate[0];
            methodId = Integer.valueOf(separate[1]);
        } catch (Exception e) {
            throw new InvalidFormatLineException();
        }
    }

    @Override
    public String toString() {
        return "Time: " + time + " isEntry: " + isEntry + "  Name: " + methodName + "  ID: " + methodId;
    }

    boolean isEntry() {
        return isEntry;
    }

    Integer getID() {
        return methodId;
    }

    String getName() {
        return methodName;
    }

    long getTime() {
        return time;
    }

}
