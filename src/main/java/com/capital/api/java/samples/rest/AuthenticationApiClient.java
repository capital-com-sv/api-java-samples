package com.capital.api.java.samples.rest;

import com.capital.api.java.samples.common.Constants;
import com.capital.api.java.samples.common.Enviroment;
import com.capital.api.java.samples.rest.dto.session.CreateSessionRequest;
import com.capital.api.java.samples.rest.dto.session.CreateSessionResponse;
import com.capital.api.java.samples.rest.dto.session.GetEncryptionKeySessionResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import static com.capital.api.java.samples.common.Constants.API_V1_SESSION;
import static com.capital.api.java.samples.common.Constants.API_V1_SESSION_ENCRYPTION_KEY;

@Service
public class AuthenticationApiClient extends AbstractApiClient {

    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final String RSA_ALGORITHM = "RSA";
    public static final String PKCS1_PADDING_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    public ConversationContext createSession(CreateSessionRequest request, String apiKey) {
        ConversationContext conversationContext = new ConversationContext(apiKey, null, null, null);
        HttpEntity requestEntity = buildHttpEntity(conversationContext, request);

        if (request.getEncryptedPassword()) {
            GetEncryptionKeySessionResponse encryptionKeyResponse = encryptionKeyResponse(conversationContext);
            String encryptedPassword = encryptPassword(encryptionKeyResponse.getEncryptionKey(), encryptionKeyResponse.getTimeStamp(), request.getPassword());
            request.setPassword(encryptedPassword);
        }

        ResponseEntity<CreateSessionResponse> response = restTemplate.exchange(getApiDomainURL() + API_V1_SESSION, HttpMethod.POST, requestEntity, CreateSessionResponse.class);

        return new ConversationContext(apiKey,
                response.getHeaders().getFirst(Constants.CLIENT_SSO_TOKEN_NAME),
                response.getHeaders().getFirst(Constants.ACCOUNT_SSO_TOKEN_NAME),
                response.getBody().getStreamingHost()
        );
    }

    public String encryptPassword(String encryptionKey, Long timestamp, String password) {
        try {
            byte[] input = stringToBytes(password + "|" + timestamp);
            input = Base64.encodeBase64(input);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(stringToBytes(encryptionKey))));

            Cipher cipher = Cipher.getInstance(PKCS1_PADDING_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(input);

            output = Base64.encodeBase64(output);
            return bytesToString(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GetEncryptionKeySessionResponse encryptionKeyResponse(ConversationContext conversationContext) {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetEncryptionKeySessionResponse> response = restTemplate.exchange(getApiDomainURL() + API_V1_SESSION_ENCRYPTION_KEY, HttpMethod.GET, requestEntity, GetEncryptionKeySessionResponse.class);
        return response.getBody();
    }

    private byte[] stringToBytes(String string) {
        return string.getBytes(CHARSET);
    }

    private String bytesToString(byte[] bytes) {
        return new String(bytes, CHARSET);
    }
}
