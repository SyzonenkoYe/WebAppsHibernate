package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums;


public enum Messages {

    OK_INS_MSG ("OFFICEWORKER insert -- SUCCESS!"),
    OK_UPD_MSG  ("OFFICEWORKER update -- SUCCESS!"),
    OK_DEL_MSG  ("OFFICEWORKER delete -- SUCCESS!"),
    ERR_INS_MSG ("OFFICEWORKER insert -- ERROR!"),

    ERR_UPD_MSG ("OFFICEWORKER update -- ERROR!"),
    ERR_DEL_MSG ("OFFICEWORKER delete -- ERROR!"),

    ERR_NOT_IN_MSG ("ERROR -- OFFICEWORKER not found in DB!"),

    ERR_DOUBLE_MSG ("ERROR -- OFFICEWORKER already present in DB!");

    private String text;

    Messages(String s) {
        text = s;
    }

    public String getText() {
        return text;
    }

    public static Messages getMessageByText(String text) {
        int index = -1;
        Messages[] messagesValues = values();
        boolean flFound = false;
        while (!flFound && index<messagesValues.length-1) {
            index++;
            if (messagesValues[index].getText().equals(text)) {
                flFound = true;
            }
        }
        Messages msg = null;
        if (!flFound) {
            msg = null;
        } else {
            msg = Messages.values()[index];
        }
        return msg;
    }

}
