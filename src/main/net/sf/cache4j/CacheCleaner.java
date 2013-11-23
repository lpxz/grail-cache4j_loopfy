package net.sf.cache4j;

/**
 * ����� CacheCleaner ��������� ������� ���������� ��������
 */
public class CacheCleaner extends Thread {

    /**
     * �������� �������
     */
    private long _cleanInterval;

    /**
     * true ���� ����� ��������� � ������
     */
    private boolean _sleep = false;

    /**
     * �����������
     * @param cleanInterval ��������(� �������������) � ������� ����� ��������� �������
     */
    public CacheCleaner(long cleanInterval) {
        _cleanInterval = cleanInterval;
        setName(this.getClass().getName());
        setDaemon(true);
    }

    /**
     * ������������� �������� �������
     * @param cleanInterval ��������(� �������������) � ������� ����� ��������� �������
     */
    public void setCleanInterval(long cleanInterval) {
        _cleanInterval = cleanInterval;
        synchronized (this) {
            if (_sleep) {
                interrupt();
            }
        }
    }

    /**
     * �������� �����. ��� ���� ����� ���������� ����� <code>clean</code>
     */
    public void run() {
        edu.hkust.clap.monitor.Monitor.loopBegin(0);
while (true) { 
edu.hkust.clap.monitor.Monitor.loopInc(0);
{
            try {
                CacheFactory cacheFactory = CacheFactory.getInstance();
                Object[] objIdArr = cacheFactory.getCacheIds();
                edu.hkust.clap.monitor.Monitor.loopBegin(1);
for (int i = 0, indx = objIdArr == null ? 0 : objIdArr.length; i < indx; i++) { 
edu.hkust.clap.monitor.Monitor.loopInc(1);
{
                    ManagedCache cache = (ManagedCache) cacheFactory.getCache(objIdArr[i]);
                    if (cache != null) {
                        cache.clean();
                    }
                    yield();
                }} 
edu.hkust.clap.monitor.Monitor.loopEnd(1);

            } catch (Throwable t) {
                t.printStackTrace();
            }
            _sleep = true;
            try {
                sleep(_cleanInterval);
            } catch (Throwable t) {
            } finally {
                _sleep = false;
            }
        }} 
//edu.hkust.clap.monitor.Monitor.loopEnd(0);

    }
}
