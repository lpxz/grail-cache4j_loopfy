package net.sf.cache4j.test;

import net.sf.cache4j.test.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.InputStream;

/**
 * ����� Tester ��������� ��� �����
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/22 16:21:10 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class Cache4jTester {

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.setProperty("net.sf.cache4j.test.BlockingCacheTest", "");
            runTest(newInstances(new ArrayList(props.keySet())));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * �������� �������� ������ ���� ��������
     * @param testList ������ ������
     */
    private static void runTest(List testList) {
        log("---------------------------------------------------------------");
        log("java.version=" + System.getProperty("java.version"));
        log("java.vm.version=" + System.getProperty("java.vm.version"));
        log("java.runtime.version=" + System.getProperty("java.runtime.version"));
        log("java.vm.name=" + System.getProperty("java.vm.name"));
        log("java.vm.info=" + System.getProperty("java.vm.info"));
        log("java.vm.vendor=" + System.getProperty("java.vm.vendor"));
        log("---------------------------------------------------------------");
        boolean success = true;
        edu.hkust.clap.monitor.Monitor.loopBegin(19);
for (int i = 0, indx = testList == null ? 0 : testList.size(); i < indx; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(19);
{
            Test test = (Test) testList.get(i);
            boolean testSuccess = true;
            log(test.getClass().getName());
            log("---------------------------------------------------------------");
            log(" STATUS |    TIME   | METHOD");
            log("---------------------------------------------------------------");
            try {
                Method[] mtds = test.getClass().getDeclaredMethods();
                test.init();
                edu.hkust.clap.monitor.Monitor.loopBegin(20);
for (int j = 0, jindx = mtds == null ? 0 : mtds.length; j < jindx; j++) { 
edu.hkust.clap.monitor.Monitor.loopInc(20);
{
                    Method m = mtds[j];
                    int modifier = m.getModifiers();
                    if (m.getReturnType() == Boolean.TYPE && m.getName().startsWith("test") && (m.getParameterTypes() == null || m.getParameterTypes().length == 0) && Modifier.isPublic(modifier)) {
                        Boolean rez = null;
                        Throwable th = null;
                        long start = 0;
                        long stop = 0;
                        try {
                            start = System.currentTimeMillis();
                            rez = (Boolean) m.invoke(test, null);
                            stop = System.currentTimeMillis();
                        } catch (Throwable t) {
                            stop = System.currentTimeMillis();
                            th = t;
                        }
                        if (rez != null && rez.booleanValue()) {
                            log("SUCCESS | " + fill("" + (stop - start), 9) + " | " + m.getName() + "()");
                        } else {
                            success = false;
                            testSuccess = false;
                            log("FAILED  | " + fill("" + (stop - start), 9) + " | " + m.getName() + "()");
                            if (th != null) {
                                th.printStackTrace();
                            }
                        }
                        try {
                            test.afterEachMethod();
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }} 
edu.hkust.clap.monitor.Monitor.loopEnd(20);

                test.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log("---------------------------------------------------------------");
            if (testSuccess) {
                log("SUCCESS");
            } else {
                log("FAILED");
            }
            log("---------------------------------------------------------------");
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(19);

        if (success) {
            log("TEST SUCCESS");
        } else {
            log("TEST FAILED");
        }
    }

    /**
     * ������ ������
     * @param classList ������ ����� � ���������� �������
     * @return ������ ����������� ������
     */
    private static List newInstances(List classList) {
        List rez = new ArrayList();
        edu.hkust.clap.monitor.Monitor.loopBegin(21);
for (int i = 0, indx = classList == null ? 0 : classList.size(); i < indx; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(21);
{
            String cl = (String) classList.get(i);
            Object obj = null;
            try {
                obj = Class.forName(cl).newInstance();
                obj = new net.sf.cache4j.test.SynchronizedCacheTest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ((obj instanceof Test)) {
                rez.add(obj);
            } else {
                log("Class:" + cl + " not instance of " + Test.class.getName());
            }
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(21);

        return rez;
    }

    /**
     * ����� �� �������
     */
    private static void log(String s) {
        System.out.println(s);
    }

    /**
     * ��������� ������ src ��������� ���� ����� ������ �� ����� ������ ���� ����� count
     * @param src �������� ������
     * @param count ����������� ������ �������������� ������
     */
    private static String fill(String src, int count) {
        src = src == null ? "" : src;
        StringBuffer buf = new StringBuffer(src);
        edu.hkust.clap.monitor.Monitor.loopBegin(22);
while (buf.length() < count) { 
edu.hkust.clap.monitor.Monitor.loopInc(22);
{
            buf.append(' ');
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(22);

        return buf.toString();
    }
}
