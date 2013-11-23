package net.sf.cache4j;

/**
 * CacheConfig ������������ ����
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:13 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public interface CacheConfig {

    /**
     * ���������� ������������� ����.
     * ������������� ������ ���� ���������� ��� ����� ����������.
     */
    public Object getCacheId();

    /**
     * ���������� �������� ���������� ����.
     */
    public String getCacheDesc();

    /**
     * ���������� ������������ ����� ����� ������� � ����.
     * 0 - ��� �����������.
     */
    public long getTimeToLive();

    /**
     * ���������� ������������ ����� ����������� ������� � ����.
     * 0 - ��� �����������.
     */
    public long getIdleTime();

    /**
     * ���������� ������������ ������ �������� � ���� (����).
     * ���� �������� ������ ���� �� ��� ��������� ������� ����������� ��� ������
     * � ����������� ������ �������� � ����.
     * 0 - ��� �����������.
     */
    public long getMaxMemorySize();

    /**
     * ���������� ������������ ���������� �������� � ����.
     * ���� �������� ������ ���� �� ��� ��������� ������� � ��� ����������� ��������
     * �� ���������� �������� � ����.
     * 0 - ��� �����������.
     */
    public int getMaxSize();

    /**
     * ���������� ��� ����.
     *  <ul>
     *   <li>blocking - (�� ���������) ����� {@link net.sf.cache4j.impl.BlockingCache}</li>
     *   <li>synchronized - ����� {@link net.sf.cache4j.impl.SynchronizedCache}</li>
     *   <li>nocache - ����� {@link net.sf.cache4j.impl.EmptyCache}</li>
     *  </ul>
     */
    public String getType();

    /**
     * ���������� �������� �������� �������� �� ����.
     * <ul>
     *   <li>lru (Least Recently Used) (�� ���������)</li>
     *   <li>lfu (Least Frequently Used)</li>
     *   <li>fifo (First In First Out)</li>
     * </ul>
     */
    public String getAlgorithm();

    /**
     * ���������� ��� ������ �� ������ ������������ � ����.
     * <ul>
     *   <li>strong - ������� ������ (�� ���������)</li>
     *   <li>soft - ������ ������ <code>java.lang.ref.SoftReference</code></li>
     * </ul>
     */
    public String getReference();
}
