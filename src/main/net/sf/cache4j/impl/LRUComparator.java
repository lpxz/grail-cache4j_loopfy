package net.sf.cache4j.impl;

import net.sf.cache4j.impl.CacheObject;
import java.util.Comparator;

/**
 * ����� LRUComparator ������������ ��� ��������� �������� � ������������ �
 * ���������� LRU (Least Recently Used).
 * ���� ��� ��������� ������ ������� ��� �������������, �� ������� ��� ����������
 * ��������, �� ��������� ������ ������� ������������� ����� ������.
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:10 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class LRUComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        CacheObject co1 = (CacheObject) o1;
        CacheObject co2 = (CacheObject) o2;
        return co1.getLastAccessTime() < co2.getLastAccessTime() ? -1 : co1.getLastAccessTime() == co2.getLastAccessTime() ? (co1.getId() < co2.getId() ? -1 : (co1.getId() == co2.getId() ? 0 : 1)) : 1;
    }

    public boolean equals(Object obj) {
        return obj == null ? false : (obj instanceof LRUComparator);
    }
}
