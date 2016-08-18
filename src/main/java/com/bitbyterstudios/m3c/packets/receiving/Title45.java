package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Title45 extends ReceivingPacket {
    private int action;
    private String titleText;
    private String subtitleText;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        action = readVarInt(buff);
        switch (action) {
            case 0: // set title
                titleText = readString(buff);
                break;
            case 1: // set subtitle
                subtitleText = readString(buff);
                break;
            case 2: // set time and display
                fadeIn = buff.getInt();
                stay = buff.getInt();
                fadeOut = buff.getInt();
                break;
            case 3:
            case 4:
                break;
            default:
                throw new IllegalStateException("Unknown action " + action + "!");
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getSubtitleText() {
        return subtitleText;
    }

    public void setSubtitleText(String subtitleText) {
        this.subtitleText = subtitleText;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }
}
