package ninja.seibert.m3c.packets.v340.login.sending;

import ninja.seibert.m3c.packets.SendingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class EncryptionResponse01 extends SendingPacket {

    private final byte[] secret;
    private final byte[] verifyToken;

    public EncryptionResponse01(byte[] secret, byte[] verifyToken) {
        super();
        this.secret = secret;
        this.verifyToken = verifyToken;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(Utilities.getVarIntWidth(secret.length) + secret.length
                + Utilities.getVarIntWidth(verifyToken.length) + verifyToken.length);
        writeVarInt(buffer, secret.length);
        buffer.put(secret);
        writeVarInt(buffer, verifyToken.length);
        buffer.put(verifyToken);
        return buffer;
    }

    @Override
    public int getType() {
        return 0x01;
    }

    public byte[] getSecret() {
        return secret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
