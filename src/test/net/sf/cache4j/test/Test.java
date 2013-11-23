package net.sf.cache4j.test;

/**
 * Test ��������� ��� ������
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:16 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public interface Test {

    /**
     * ���������� ����� ������� ������������
     * @throws java.lang.Exception
     */
    public void init() throws Exception;

    /**
     * ���������� ����� ���������� ������� ��������� ������
     * @throws java.lang.Exception
     */
    public void afterEachMethod() throws Exception;

    /**
     * ���������� ����� ������������
     * @throws java.lang.Exception
     */
    public void destroy() throws Exception;
}
