/*
 * Copyright 2022 jrosclient project
 * 
 * Website: https://github.com/lambdaprime/jrosmessages
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.jros1messages.impl;

import id.kineticstreamer.InputKineticStream;
import id.kineticstreamer.KineticStreamReader;
import id.xfunction.logging.XLogger;
import java.io.DataInput;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;

public class RosDataInput implements InputKineticStream {

    private static final XLogger LOGGER = XLogger.getLogger(RosDataInput.class);

    private DataInput in;

    public RosDataInput(DataInput in) {
        this.in = in;
    }

    @Override
    public int readInt(Annotation[] fieldAnnotations) throws IOException {
        return Integer.reverseBytes(in.readInt());
    }

    @Override
    public String readString(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readString");
        int len = readLen();
        byte[] b = new byte[len];
        in.readFully(b);
        var value = new String(b, 0, len);
        LOGGER.exiting("readString", value);
        return value;
    }

    public int readLen() throws IOException {
        return Integer.reverseBytes(in.readInt());
    }

    @Override
    public double readDouble(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readDouble");
        var value =
                Double.longBitsToDouble(
                        Long.reverseBytes(Double.doubleToRawLongBits(in.readDouble())));
        LOGGER.exiting("readDouble", value);
        return value;
    }

    @Override
    public float readFloat(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readFloat");
        var value =
                Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(in.readFloat())));
        LOGGER.exiting("readFloat", value);
        return value;
    }

    @Override
    public boolean readBool(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readBool");
        var value = in.readBoolean();
        LOGGER.exiting("readBool", value);
        return value;
    }

    @Override
    public Object[] readArray(Object[] arg0, Class<?> type, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readArray");
        var array = (Object[]) Array.newInstance(type, readLen());
        for (int i = 0; i < array.length; i++) {
            array[i] = new KineticStreamReader(this).read(type);
        }
        LOGGER.exiting("readArray");
        return array;
    }

    @Override
    public void close() throws Exception {
        // nothing to release
    }

    @Override
    public byte readByte(Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readByte");
        var value = in.readByte();
        LOGGER.exiting("readByte");
        return value;
    }

    @Override
    public byte[] readByteArray(byte[] arg0, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readByteArray");
        var array = new byte[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.readByte();
        }
        LOGGER.exiting("readByteArray");
        return array;
    }

    @Override
    public int[] readIntArray(int[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] readDoubleArray(double[] arg0, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readDoubleArray");
        var array = new double[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readDouble(fieldAnnotations);
        }
        LOGGER.exiting("readDoubleArray");
        return array;
    }

    @Override
    public boolean[] readBooleanArray(boolean[] arg0, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readBooleanArray");
        var array = new boolean[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readBool(fieldAnnotations);
        }
        LOGGER.exiting("readBooleanArray");
        return array;
    }

    @Override
    public long readLong(Annotation[] fieldAnnotations) throws Exception {
        return Long.reverseBytes(in.readLong());
    }

    @Override
    public long[] readLongArray(long[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short readShort(Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short[] readShortArray(short[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] readStringArray(String[] arg0, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readStringArray");
        var array = new String[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readString(fieldAnnotations);
        }
        LOGGER.exiting("readStringArray");
        return array;
    }

    @Override
    public char readChar(Annotation[] arg0) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public char[] readCharArray(char[] arg0, Annotation[] arg1) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public float[] readFloatArray(float[] array, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }
}
