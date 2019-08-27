package shiroTest;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

public class TestEncode {
    @Test
    public void testBase64() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        System.out.println(base64Encoded);
        System.out.println(str2);
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testHexEncode() {
        String str = "hello";
        String hexEncoded = Hex.encodeToString(str.getBytes());
        System.out.println(hexEncoded);
        String str2 = new String(Hex.decode(hexEncoded.getBytes()));
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testCodecSupport() {
        String str = "hello";
        byte[] aa = CodecSupport.toBytes(str, "utf-8");
        for (int i=0;i<aa.length;i++){
            System.out.println((char)aa[i]);
        }

        String str2 = CodecSupport.toString(aa, "utf-8");
        System.out.println(str2);
        Assert.assertEquals(str, str2);
    }

    @Test
    public void TestMd5() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt,2).toString();
        System.out.println(md5);
    }

    @Test
    public void testSha256Hash() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void generalEncode() {
        String str = "hello";
        String salt = "123";
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);
    }

    @Test
    public void AESTest() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);
        //解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text2);
        Assert.assertEquals(text,text2);
    }
}
