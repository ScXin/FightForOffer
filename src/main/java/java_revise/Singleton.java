package java_revise;

/**
 * @author ScXin
 * @date 8/12/2019 12:08 AM
 */

public enum Singleton {
    INSTANCE;

    // 这里隐藏了一个空的私有构造方法
    private Singleton() {
    }

}