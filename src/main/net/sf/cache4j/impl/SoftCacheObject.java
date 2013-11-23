package net.sf.cache4j.impl;

import java.lang.ref.SoftReference;

/**
 * ����� SoftCacheObject ��� ������ :) �������� ��� ���������� ��������.
 * ��� �������� ������ �� ������ ������������ ����� <code>java.lang.ref.SoftReference</code>
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:10 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class SoftCacheObject extends CacheObject {

    /**
     * �����������
     * @param objId ������������� ����������� �������
     */
    SoftCacheObject(Object objId) {
        super(objId);
    }

    /**
     * ���������� ���������� ������
     */
    Object getObject() {
        return _obj == null ? null : ((SoftReference) _obj).get();
    }

    /**
     * ������������� ���������� ������
     */
    void setObject(Object obj) {
        _obj = obj == null ? null : new SoftReference(obj);
    }
}
