package net.sf.cache4j;

/**
 * ��������� CacheInfo ������������� �������������� ���������� � ����
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:14 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public interface CacheInfo {

    /**
     * ���������� ���������� ������� ������� ������ <code>Cache.get()</code> (����� ������ ������).
     */
    public long getCacheHits();

    /**
     * ���������� ���������� ��������� ������� ������ <code>Cache.get()</code> (����� ������ <code>null</code>).
     */
    public long getCacheMisses();

    /**
     * ���������� ���������� ������� ������ <code>Cache.put()</code>
     */
    public long getTotalPuts();

    /**
     * ���������� ���������� ������� ������ <code>Cache.remove()</code>
     */
    public long getTotalRemoves();

    /**
     * ���������� ���������� ����
     */
    public void reset();

    /**
     * ���������� ����(� ������) ���������� ��������� ����
     */
    public long getMemorySize();
}
