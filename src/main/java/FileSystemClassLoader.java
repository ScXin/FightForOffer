/**
 * @author ScXin
 * @date 5/10/2019 8:46 PM
 */

import java.io.*;

/**
 * 自定义类加载器得实现
 */
public class FileSystemClassLoader extends ClassLoader {
    private String rootDir;
    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }
    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            FileInputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}