import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ObjectFactory {

    private static Log log = LogFactory.getLog(ObjectFactory.class);

    public static Object create(String name) throws Exception {
        if (StringUtils.isBlank(name)) return null;
        ClassLoader clazzLoader = ObjectFactory.class.getClassLoader();
        Class clazz;
        clazz = clazzLoader.loadClass(name);
        return clazz.newInstance();
    }

    public static Object create(URL[] urls, String name) throws Exception {
        if (StringUtils.isBlank(name)) return null;
        URLClassLoader clazzLoader;
        Class clazz;
        clazzLoader = new URLClassLoader(urls);
        clazz = clazzLoader.loadClass(name);
        return clazz.newInstance();
    }

    public static Object create(String filePath, String name) throws Exception {
        if (StringUtils.isBlank(name)) return null;
        URLClassLoader clazzLoader;
        Class clazz;
        ClassLoaderUtil.addFile(filePath);
        filePath = "jar:file://" + filePath + "!/";
        URL url = new File(filePath).toURL();
        clazzLoader = new URLClassLoader(new URL[]{url});
        clazz = clazzLoader.loadClass(name);
        return clazz.newInstance();


    }


}
