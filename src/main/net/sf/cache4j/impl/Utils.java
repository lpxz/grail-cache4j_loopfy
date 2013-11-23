package net.sf.cache4j.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * ����� Utils �������� ������ ��� ������ � �������������� ���������
 *
 * @version $Revision: 1.1 $ $Date: 2010/06/18 17:01:10 $
 * @author Yuriy Stepovoy. <a href="mailto:stepovoy@gmail.com">stepovoy@gmail.com</a>
 **/
public class Utils {

    /**
     * ���������� ������ ������� � ������. ���� ������ ����� null ����� ����� 0.
     * @param o ������. ������ ������ ����������� ��������� Serializable �����
     * ����� ������� ����������.
     * @return ������ ������� � ������ ��� 0 ���� ������ ����� null
     * @throws java.io.IOException
     */
    public static int size(final Object o) throws IOException {
        if (o == null) {
            return 0;
        }
        ByteArrayOutputStream buf = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(o);
        out.flush();
        buf.close();
        return buf.size();
    }

    /**
     * ���������� ����� ����������� �������. ��� ����������� ������������
     * ������������/��������������.
     * @param o ������. ������ ������ ����������� ��������� Serializable �����
     * ����� ������� ����������.
     * @return ���������� ����� ������ ��� null ���� ������ ����� null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object copy(final Object o) throws IOException, ClassNotFoundException {
        if (o == null) {
            return null;
        }
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(4096);
        ObjectOutput out = new ObjectOutputStream(outbuf);
        out.writeObject(o);
        out.flush();
        outbuf.close();
        ByteArrayInputStream inbuf = new ByteArrayInputStream(outbuf.toByteArray());
        ObjectInput in = new ObjectInputStream(inbuf);
        return in.readObject();
    }
}
