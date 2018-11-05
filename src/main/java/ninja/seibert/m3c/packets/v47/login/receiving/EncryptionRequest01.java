package ninja.seibert.m3c.packets.v47.login.receiving;

import ninja.seibert.m3c.ApiAccess;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.login.receiving.EncryptionRequest;
import ninja.seibert.m3c.packets.v47.login.sending.EncryptionResponse01;
import ninja.seibert.m3c.util.CryptoHelper;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.PublicKey;

public class EncryptionRequest01 extends EncryptionRequest {
    private String serverId;
    private PublicKey pubKey;
    private byte[] verifyToken;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        serverId = readString(buff);
        byte[] pubKeyBytes = bytesFromBuff(buff);
        pubKey = CryptoHelper.decodePublicKey(pubKeyBytes);
        verifyToken = bytesFromBuff(buff);
    }

    public void encrypt(ConnectionHandler handler) throws IOException {
        SecretKey secretKey = CryptoHelper.createNewSharedKey();//generate a secret key
        String servId = CryptoHelper.getServerIdHash(getServerId(), getPubKey(), secretKey);
        ApiAccess.sendSessionRequest(handler.getData().getAccessToken(), servId, handler.getData().getUuid());
        byte[] secret = CryptoHelper.encryptData(getPubKey(), secretKey.getEncoded());
        byte[] verify = CryptoHelper.encryptData(getPubKey(), getVerifyToken());
        handler.addPacketToSend(new EncryptionResponse01(secret, verify));
        handler.encryptStreams(secretKey);
    }

    public String getServerId() {
        return serverId;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
