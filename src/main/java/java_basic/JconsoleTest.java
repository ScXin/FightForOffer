package java_basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ScXin
 * @date 9/2/2019 3:44 PM
 */
public class JconsoleTest {
    static class OOMobject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMobject> list = new ArrayList<OOMobject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMobject());
        }
    }

    public static void main(String[] args) throws Exception {
    fillHeap(2000);
    }
}
