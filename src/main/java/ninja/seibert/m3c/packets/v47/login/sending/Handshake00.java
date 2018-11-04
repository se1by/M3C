package ninja.seibert.m3c.packets.v47.login.sending;

import ninja.seibert.m3c.packets.SendingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class Handshake00 extends SendingPacket {
    private int protocolVersion;
    private String host;
    private short port;
    private int nextState;

    public Handshake00(int protocolVersion, String host, short port) {
        this.protocolVersion = protocolVersion;
        this.host = host;
        this.port = port;
        nextState = 2;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketSize());
        writeVarInt(buffer, protocolVersion);
        writeVarInt(buffer, host.length());
        buffer.put(host.getBytes());
        buffer.putShort(port);
        writeVarInt(buffer, nextState);
        return buffer;
    }

    private int getPacketSize() {
        return Utilities.getVarIntWidth(protocolVersion) + Utilities.getVarIntWidth(host.length())
                + host.getBytes().length + 2 + Utilities.getVarIntWidth(nextState);
    }

    @Override
    public int getType() {
        return 0x00;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }
}
