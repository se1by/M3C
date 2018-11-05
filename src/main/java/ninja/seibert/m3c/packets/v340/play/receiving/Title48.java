package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Title48 extends ReceivingPacket {
    private int action;
    private String titleText;
    private String subtitleText;
    private String actionbarText;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.action = readVarInt(buff);
        switch (this.action) {
            case 0: // set title
                this.titleText = readString(buff);
                break;
            case 1: // set subtitle
                this.subtitleText = readString(buff);
                break;
            case 2:
                this.actionbarText = readString(buff);
                break;
            case 3: // set time and display
                this.fadeIn = buff.getInt();
                this.stay = buff.getInt();
                this.fadeOut = buff.getInt();
                break;
            case 4:
            case 5:
                break;
            default:
                throw new IllegalStateException("Unknown action " + action + "!");
        }
    }

    public int getAction() {
        return action;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getSubtitleText() {
        return subtitleText;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

}
