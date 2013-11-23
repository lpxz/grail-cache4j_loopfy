package net.sf.cache4j.impl;

import net.sf.cache4j.CacheException;

/**
 * ����� CacheObject ��� �������� ��� ���������� ��������.
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:11 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class CacheObject {

    /**
     * ������������� �������
     */
    private Object _objId;

    /**
     * ���������� ������
     */
    protected Object _obj;

    /**
     * ���������� ��������� � �������
     */
    private long _accessCount;

    /**
     * ����� �������� �������
     */
    private long _createTime;

    /**
     * ����� ���������� ������� � �������
     */
    private long _lastAccessTime;

    /**
     * ������ ������� � ������
     */
    private int _objSize;

    /**
     * ����� ����������� ������
     */
    private Thread _lockThread;

    /**
     * ���������� ������������� �������
     */
    private long _id;

    /**
     * �����������
     * @param objId ������������� ����������� �������
     */
    CacheObject(Object objId) {
        _objId = objId;
        _obj = null;
        _createTime = System.currentTimeMillis();
        _accessCount = 1;
        _lastAccessTime = _createTime;
        _objSize = 0;
        _lockThread = null;
        _id = nextId();
    }

    /**
     * ��������� ��� ������ ������ ������, ���� ����� �������������� ��� ������
     * ����� ���� �������. � �������� ������ ������ ���� ����� ����� ��������
     * ������������ ���������� ���, ��� �� ����� ��������� � ���������� ��������
     * ������.
     * @throws CacheException
     */
    synchronized void lock() throws CacheException {
        if (_lockThread != null && Thread.currentThread() == _lockThread) {
            return;
        }
        try {
            edu.hkust.clap.monitor.Monitor.loopBegin(23);
while (_lockThread != null) { 
edu.hkust.clap.monitor.Monitor.loopInc(23);
{
                wait();
            }} 
edu.hkust.clap.monitor.Monitor.loopEnd(23);

            _lockThread = Thread.currentThread();
        } catch (InterruptedException ex) {
            notify();
            throw new CacheException(ex.getMessage());
        }
    }

    /**
     * ������� ���������� � ������� � ����� ���� ����� ��������� ���������� ��
     * ������� ������.
     */
    synchronized void unlock() {
        _lockThread = null;
        notify();
    }

    /**
     * ���������� ���������� ������
     */
    Object getObject() {
        return _obj;
    }

    /**
     * ������������� ���������� ������
     */
    void setObject(Object obj) {
        _obj = obj;
    }

    /**
     * ���������� ������������� ����������� �������
     */
    Object getObjectId() {
        return _objId;
    }

    /**
     * ���������� ���������� ��������� � �������
     */
    long getAccessCount() {
        return _accessCount;
    }

    /**
     * ���������� ����� �������� ������� � �������������
     */
    long getCreateTime() {
        return _createTime;
    }

    /**
     * ���������� ����� ���������� ������� � �������������
     */
    long getLastAccessTime() {
        return _lastAccessTime;
    }

    /**
     * ���������� ������ ������� � ������
     */
    long getObjectSize() {
        return _objSize;
    }

    /**
     * ������������� ������ ������� � ������
     */
    void setObjectSize(int objSize) {
        _objSize = objSize;
    }

    /**
     * ��������� ���������� �� �������
     */
    void updateStatistics() {
        _accessCount++;
        _lastAccessTime = System.currentTimeMillis();
        _id = nextId();
    }

    /**
     * ���������� ���������� �������
     */
    void reset() {
        _obj = null;
        _objSize = 0;
        _createTime = System.currentTimeMillis();
        _accessCount = 1;
        _lastAccessTime = _createTime;
        _id = nextId();
    }

    long getId() {
        return _id;
    }

    /**
     * ���������� ��������� ������������� �������
     */
    public String toString() {
        return "id:" + _objId + " createTime:" + _createTime + " lastAccess:" + _lastAccessTime + " accessCount:" + _accessCount + " size:" + _objSize + " object:" + _obj;
    }

    /**
     * �������
     */
    private static long ID = 0;

    /**
     * ���������� ��������� ���������� �������������
     */
    private static synchronized long nextId() {
        return ID++;
    }
}
