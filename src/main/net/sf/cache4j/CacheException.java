package net.sf.cache4j;

/**
 * CacheException ���������� �������� �����
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:12 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class CacheException extends Exception {

    /**
     * �����������
     */
    public CacheException() {
    }

    /**
     * ����������� � ��������� ���������
     * @param msg ���������
     */
    public CacheException(String msg) {
        super(msg);
    }
}
