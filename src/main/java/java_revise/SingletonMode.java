package java_revise;



/**
 * @author ScXin
 * @date 8/11/2019 11:46 PM
 */
public class SingletonMode {
    /**
     * 饿汉式
     */
//    private static SingleTom singleTom = new SingleTom(10);
//
//    public SingleTom get() {
//        return singleTom;
//    }
    /**
     * 懒汉式
     */

    private static SingleTom singleTom = null;

//    public SingleTom get() {
//
//        if (singleTom != null) {
//            return singleTom;
//        } else {
//            singleTom=new SingleTom();
//            return singleTom;
//        }
//    }

    /**
     * 双重检查
     */
//    public SingleTom get() {
//        if (singleTom != null) {
//            return singleTom;
//        }
//        synchronized (SingleTom.class) {
//            if (singleTom != null) {
//                return singleTom;
//            }
//            singleTom = new SingleTom();
//            return singleTom;
//        }
//    }


    /**
     * 静态内部类
     */
//    public static class innerSingle {
//        private static SingleTom singleTom = new SingleTom();
//    }
//
//    public SingleTom get() {
//        return innerSingle.singleTom;
//    }

    /**
     * 枚举类型
     */

}



