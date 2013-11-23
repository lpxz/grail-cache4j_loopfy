package net.sf.cache4j.test;

import net.sf.cache4j.CacheFactory;
import net.sf.cache4j.CacheConfig;
import net.sf.cache4j.CacheException;
import net.sf.cache4j.Cache;
import net.sf.cache4j.impl.BlockingCache;
import net.sf.cache4j.impl.CacheConfigImpl;
import java.util.Random;

/**
 * ����� BlockingCacheTest ��������� BlockingCache
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:16 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class BlockingCacheTest implements Test {

    /**
     * ���������� ����� ������� ������������
     * @throws java.lang.Exception
     */
    public void init() throws Exception {
    }

    /**
     * ���������� ����� ���������� ������� ��������� ������
     * @throws java.lang.Exception
     */
    public void afterEachMethod() throws Exception {
        CacheFactory cf = CacheFactory.getInstance();
        Object[] cacheIds = cf.getCacheIds();
        edu.hkust.clap.monitor.Monitor.loopBegin(2);
for (int i = 0, indx = cacheIds == null ? 0 : cacheIds.length; i < indx; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(2);
{
            cf.removeCache(cacheIds[i]);
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(2);

    }

    /**
     * ���������� ����� ������������
     * @throws java.lang.Exception
     */
    public void destroy() throws Exception {
    }

    /**
     * �������� ������ � ������ ��� �� ����.
     */
    public static boolean test_PUT_GET() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        return cache.get(key) != null ? true : false;
    }

    /**
     * �������� ������. �������� null. ��������� ������ ���� null.
     */
    public static boolean test_PUT_OBJ_PUT_NULL_GET() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        cache.put(key, null);
        return cache.get(key) == null ? true : false;
    }

    /**
     * �������� ������. ������� ������. ��������� ������ ���� null.
     */
    public static boolean test_PUT_REMOVE_GET() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        cache.remove(key);
        return cache.get(key) == null ? true : false;
    }

    /**
     * �������� ������. ������� ���. ��������� ������ ���� null.
     */
    public static boolean test_PUT_CLEAR_GET() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        cache.clear();
        return cache.get(key) == null ? true : false;
    }

    /**
     * ������������� ������������ ���������� ��������� � ����. �������� ������
     * ��� ����������. ����������� ������ ���� ������������ ���������� ��������.
     */
    public static boolean test_MAXSIZE() throws Exception {
        BlockingCache cache = new BlockingCache();
        int maxSize = 100;
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, maxSize, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        edu.hkust.clap.monitor.Monitor.loopBegin(3);
for (int i = 0, maxi = maxSize * 2; i < maxi; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(3);
{
            cache.put(new Long(i), new Long(i));
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(3);

        return cache.size() == maxSize ? true : false;
    }

    /**
     * ������������� ������������ ������ ������ ���������� ��������� ����.
     * �������� ������� ������� �������� ����� ��� ��������� ������������ ������.
     * � ���������� ������� � ���� ������ �������� ������ �� �����������
     * ������������.
     */
    public static boolean test_MAXMEMORYSIZE() throws Exception {
        BlockingCache cache = new BlockingCache();
        int maxMemorySize = 1000;
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, maxMemorySize, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        edu.hkust.clap.monitor.Monitor.loopBegin(4);
for (int i = 0; i < 1000; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(4);
{
            cache.put(new Long(i), new Long(i));
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(4);

        return cache.getCacheInfo().getMemorySize() <= maxMemorySize ? true : false;
    }

    /**
     * ������������� ������������ ���������� � ������������ ���� �������� � ����.
     * �������� ������� �������� ����� ��� ��������� ������������ ���������� � �����.
     * � ���������� ���������� �������� � ����� �� ������ ��������� ������������
     * ��������.
     */
    public static boolean test_MAXSIZE_AND_MAXMEMORYSIZE() throws Exception {
        BlockingCache cache = new BlockingCache();
        int maxMemorySize = 1000;
        int maxSize = 1000;
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, maxMemorySize, maxSize, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        edu.hkust.clap.monitor.Monitor.loopBegin(5);
for (int i = 0; i < maxSize * 2; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(5);
{
            cache.put(new Long(i), new Long(i));
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(5);

        return cache.getCacheInfo().getMemorySize() <= maxMemorySize && cache.size() <= maxSize ? true : false;
    }

    /**
     * ������������� ������������ ����� ����� �������. �������� ������.
     * ������������� ����� �� ����� ����������� ������������ �����.
     * � ���������� ��� ������ ������� null.
     */
    public static boolean test_TTL() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 1, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        Thread.sleep(30);
        return cache.get(key) == null ? true : false;
    }

    /**
     * ������������� ������������ ����� ����������� �������. �������� ������.
     * ������������� ����� �� ����� ����������� ������������ �����.
     * � ���������� ��� ������ ������� null.
     */
    public static boolean test_IDLE() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 1, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object key = "key";
        Object value = "value";
        cache.put(key, value);
        Thread.sleep(30);
        return cache.get(key) == null ? true : false;
    }

    /**
     * ��������� ��� �������� ������������� ������� �������� � ������� �����������
     * ����� �����. ��������� ������� � ������������ �������� ����� �,
     * ������������� �������� ������� X*2, �������� ������� ����� �� �*3.
     * � ���������� ��� ������ ���� �������� ������.
     */
    public static boolean test_CACHE_CLEANER_TTL() throws Exception {
        CacheFactory cf = CacheFactory.getInstance();
        long ttl = 100;
        long cleanInterval = ttl * 2;
        long sleep = ttl * 3;
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, ttl, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        edu.hkust.clap.monitor.Monitor.loopBegin(4);
for (int i = 0; i < 1000; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(4);
{
            cache.put(new Long(i), new Long(i));
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(4);

        cf.addCache(cache);
        cf.setCleanInterval(cleanInterval);
        Thread.sleep(sleep);
        return cache.size() == 0 ? true : false;
    }

    /**
     * ��������� ��� �������� ������������� ������� �������� � ������� �����������
     * ����� �����������. ��������� ������� � ������������ �������� ����������� �,
     * ������������� �������� ������� X*2, �������� ������� ����� �� �*3.
     * � ���������� ��� ������ ���� �������� ������.
     */
    public static boolean test_CACHE_CLEANER_IDLE() throws Exception {
        CacheFactory cf = CacheFactory.getInstance();
        long idle = 100;
        long cleanInterval = idle * 2;
        long sleep = idle * 3;
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, idle, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        edu.hkust.clap.monitor.Monitor.loopBegin(4);
for (int i = 0; i < 1000; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(4);
{
            cache.put(new Long(i), new Long(i));
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(4);

        cf.addCache(cache);
        cf.setCleanInterval(cleanInterval);
        Thread.sleep(sleep);
        return cache.size() == 0 ? true : false;
    }

    /**
     * ��������� �������� ���������� LRU. ��� ������������ ���� ������ ���������
     * ������ � ���������� �������� �������������.
     */
    public static boolean test_EVICTION_ALGORITHM_LRU() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 2, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        Object o1 = "o1";
        Object o2 = "o2";
        Object o3 = "o3";
        cache.put(o1, o1);
        Thread.sleep(50);
        cache.put(o2, o2);
        Thread.sleep(50);
        cache.get(o1);
        cache.put(o3, o3);
        return cache.get(o1) != null && cache.get(o3) != null && cache.get(o2) == null ? true : false;
    }

    /**
     * ��������� �������� ���������� LFU. ��� ������������ ���� ������ ���������
     * ������ ������� ������������� ���������� ���������� ���.
     */
    public static boolean test_EVICTION_ALGORITHM_LFU() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 2, null, "lfu", "strong");
        cache.setCacheConfig(cacheConfig);
        Object o1 = "o1";
        Object o2 = "o2";
        Object o3 = "o3";
        cache.put(o1, o1);
        cache.get(o1);
        Thread.sleep(50);
        cache.put(o2, o2);
        cache.put(o3, o3);
        return cache.get(o1) != null && cache.get(o3) != null && cache.get(o2) == null ? true : false;
    }

    /**
     * ��������� �������� ���������� FIFO. ��� ������������ ���� ������ ���������
     * ������ � ���������� �������� ��������.
     */
    public static boolean test_EVICTION_ALGORITHM_FIFO() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 2, null, "fifo", "strong");
        cache.setCacheConfig(cacheConfig);
        Object o1 = "o1";
        Object o2 = "o2";
        Object o3 = "o3";
        cache.put(o1, o1);
        Thread.sleep(50);
        cache.put(o2, o2);
        Thread.sleep(50);
        cache.put(o3, o3);
        return cache.get(o2) != null && cache.get(o3) != null && cache.get(o1) == null ? true : false;
    }

    /**
     * ��������� ��� ����� � �������� strong. ��� ��������� �������� ����������
     * �������� ������ �������� ���������� OutOfMemoryError.
     */
    public static boolean test_REFERENCE_STRONG() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        int i = 0;
        try {
            edu.hkust.clap.monitor.Monitor.loopBegin(6);
for (; i < 10; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(6);
{
                cache.put(new Integer(i), new Long[2048 * 2048]);
            }} 
edu.hkust.clap.monitor.Monitor.loopEnd(6);

        } catch (OutOfMemoryError o) {
            cache.clear();
            return true;
        }
        return false;
    }

    /**
     * ��������� ��� ����� � �������� soft. ��� ��������� �������� ����������
     * �������� ���������� OutOfMemoryError �� ������ ��������.
     */
    public static boolean test_REFERENCE_SOFT() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "soft");
        cache.setCacheConfig(cacheConfig);
        int i = 0;
        try {
            edu.hkust.clap.monitor.Monitor.loopBegin(6);
for (; i < 10; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(6);
{
                cache.put(new Integer(i), new Long[2048 * 2048]);
            }} 
edu.hkust.clap.monitor.Monitor.loopEnd(6);

        } catch (OutOfMemoryError o) {
            cache.clear();
            return false;
        }
        return cache.size() == i;
    }

    /**
     * ��������� ����������� ���� �������� �� ������������ �������� ������������.
     * ���� �������� ������ � ��� ������ null �� � ��� ����� ��������� ������ �
     * ������ ���������� � ������ get(). ���� �������� ������ � ������ ������,
     * ��������� ������, ������� ������ �� ��� ������ ������ ����������.
     */
    public static boolean test_BLOCKING() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 0, null, "lru", "soft");
        cache.setCacheConfig(cacheConfig);
        cache.get("1");
        int exceptCount = 0;
        try {
            cache.get("2");
        } catch (CacheException o) {
            exceptCount++;
        }
        try {
            cache.put("3", "3");
        } catch (CacheException o) {
            exceptCount++;
        }
        try {
            cache.remove("4");
        } catch (CacheException o) {
            exceptCount++;
        }
        try {
            cache.put("1", "1");
        } catch (CacheException o) {
            exceptCount++;
        }
        return exceptCount == 3;
    }

    /**
     * ��������� ������ ���� � ��������
     */
    public static boolean test_THREAD1() throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 1000, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        int tcount = 10;
        int count = 10000;
        edu.hkust.clap.monitor.Monitor.loopBegin(7);
for (int i = 0; i < tcount; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(7);
{
            new TThread(cache, count).start();
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(7);

        edu.hkust.clap.monitor.Monitor.loopBegin(8);
while (_threadCount != 0) { 
edu.hkust.clap.monitor.Monitor.loopInc(8);
{
            Thread.currentThread().yield();
            Thread.currentThread().sleep(1);
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(8);

        return true;
    }

    public static void main(String[] args) throws Exception {
        BlockingCache cache = new BlockingCache();
        CacheConfig cacheConfig = new CacheConfigImpl("cacheId", null, 0, 0, 0, 1000, null, "lru", "strong");
        cache.setCacheConfig(cacheConfig);
        int tcount = 10;
        int count = 100000;
        TThread[] threads = new TThread[tcount];
        edu.hkust.clap.monitor.Monitor.loopBegin(9);
for (int i = 0; i < tcount; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(9);
{
            threads[i] = new TThread(cache, count);
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(9);

        long start = System.currentTimeMillis();
        edu.hkust.clap.monitor.Monitor.loopBegin(10);
for (int i = 0; i < tcount; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(10);
{
            threads[i].start();
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(10);

        edu.hkust.clap.monitor.Monitor.loopBegin(11);
for (int i = 0; i < tcount; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(11);
{
            threads[i].join();
        }} 
edu.hkust.clap.monitor.Monitor.loopEnd(11);

        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));
        return;
    }

    private static int _threadCount;

    private static long _id;

    static synchronized void incThreadCount() {
        _threadCount++;
    }

    static synchronized void decThreadCount() {
        _threadCount--;
    }

    static synchronized long nextId() {
        return _id++;
    }

    static class TThread extends Thread {

        private Cache _cache;

        private long _count;

        private Random _rnd = new Random(nextId());

        public TThread(Cache cache, long count) {
            incThreadCount();
            _cache = cache;
            _count = count;
        }

        public void run() {
            long count = 0;
            try {
                edu.hkust.clap.monitor.Monitor.loopBegin(12);
while (count < _count) { 
edu.hkust.clap.monitor.Monitor.loopInc(12);
{
                    count++;
                    Object key = new Long(_rnd.nextInt(1500));
                    _cache.get(key);
                    _cache.put(key, key);
                }} 
edu.hkust.clap.monitor.Monitor.loopEnd(12);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                decThreadCount();
            }
        }
    }
}
