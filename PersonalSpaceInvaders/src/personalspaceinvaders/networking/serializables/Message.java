package personalspaceinvaders.networking.serializables;

import java.io.Serializable;

/**
 *
 * @author SHerbocopter
 */
public class Message implements Serializable {
    public enum MsgType {
        DUMMY,
        MSG_TEXT, //data field is null
        MSG_WAVES,
        MSG_ROUNDRES,
        MSG_DISCONNECT //not necessary
    };
    
    public MsgType type = MsgType.DUMMY;
    public SerData data;
    public String text;
    
    public Message() {
        type = MsgType.DUMMY;
        data = null;
        text = "";
    }
}
