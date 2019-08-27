import org.junit.Test;

import java.util.UUID;

public class test1 {
    @Test
    public void uuidTest() {
        for(int i=0;i<100;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }

}