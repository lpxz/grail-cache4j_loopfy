package net.sf.cache4j;

import net.sf.cache4j.CacheException;
import net.sf.cache4j.CacheConfig;

/**
 * ��������� ManagedCache ������������� ������ ���������� ����������� ����
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:14 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public interface ManagedCache {

    /**
     * ������������� ������������ ����.
     * ��� ��������� ������������ ��� ������� ���� ��������.
     * @param config ������������
     * @throws CacheException ���� �������� ��������
     */
    public void setCacheConfig(CacheConfig config) throws CacheException;

    /**
     * ��������� ������� ����.
     * ��������� ������� � ������� ����������� ����� ����� ��� ��������� �����
     * �����������.
     * @throws CacheException ���� �������� ��������
     */
    public void clean() throws CacheException;
}
