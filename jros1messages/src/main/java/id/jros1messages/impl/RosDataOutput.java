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

import static id.kineticstreamer.KineticStreamConstants.EMPTY_ANNOTATIONS;

import id.kineticstreamer.KineticStreamController;
import id.kineticstreamer.KineticStreamWriter;
import id.kineticstreamer.OutputKineticStream;
import id.xfunction.logging.XLogger;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;

public class RosDataOutput implements OutputKineticStream {
    private static final XLogger LOGGER = XLogger.getLogger(RosDataOutput.class);
    private DataOutput out;
    private KineticStreamController controller;

    public RosDataOutput(DataOutput out, KineticStreamController controller) {
        this.out = out;
        this.controller = controller;
    }

    public void writeLen(int len) throws IOException {
        writeInt(len, EMPTY_ANNOTATIONS);
    }

    private void writeArraySize(int len, Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("writeArraySize");
        for (int i = 0; i < fieldAnnotations.length; i++) {
            if (fieldAnnotations[i] instanceof id.jrosmessages.Array a) {
                if (a.size() > 0) return;
            }
        }
        writeLen(len);
        LOGGER.exiting("writeArraySize");
    }

    @Override
    public void writeString(String str, Annotation[] fieldAnnotations) throws IOException {
        var len = str.length();
        writeLen(len);
        out.write(str.getBytes());
    }

    @Override
    public void writeInt(Integer i, Annotation[] fieldAnnotations) throws IOException {
        out.writeInt(Integer.reverseBytes(i));
    }

    @Override
    public void writeDouble(Double f, Annotation[] fieldAnnotations) throws IOException {
        out.writeDouble(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(f))));
    }

    @Override
    public void writeFloat(Float f, Annotation[] fieldAnnotations) throws IOException {
        out.writeFloat(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f))));
    }

    @Override
    public void writeBoolean(Boolean b, Annotation[] fieldAnnotations) throws IOException {
        writeByte(b ? (byte) 1 : (byte) 0, fieldAnnotations);
    }

    @Override
    public void writeArray(Object[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        var writer = new KineticStreamWriter(this).withController(controller);
        for (var item : array) {
            writer.write(item);
        }
    }

    @Override
    public void close() throws Exception {
        // nothing to release
    }

    @Override
    public void writeByte(Byte b, Annotation[] fieldAnnotations) throws IOException {
        out.writeByte(b);
    }

    @Override
    public void writeIntArray(int[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            var buf = new byte[array.length * Integer.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRosMessagesConstants.ROS_BYTE_ORDER)
                    .asIntBuffer()
                    .put(array);
            out.write(buf);
        }
    }

    @Override
    public void writeByteArray(byte[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        out.write(array);
    }

    @Override
    public void writeDoubleArray(double[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            var buf = new byte[array.length * Double.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRosMessagesConstants.ROS_BYTE_ORDER)
                    .asDoubleBuffer()
                    .put(array);
            out.write(buf);
        }
    }

    @Override
    public void writeBooleanArray(boolean[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        var buf = new byte[array.length];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = array[i] ? (byte) 1 : (byte) 0;
        }
        writeByteArray(buf, EMPTY_ANNOTATIONS);
    }

    @Override
    public void writeLong(Long i, Annotation[] fieldAnnotations) throws Exception {
        out.writeLong(Long.reverseBytes(i));
    }

    @Override
    public void writeShort(Short arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeShortArray(short[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeStringArray(String[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        for (var item : array) {
            writeString(item, EMPTY_ANNOTATIONS);
        }
    }

    @Override
    public void writeChar(Character arg0, Annotation[] arg1) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCharArray(char[] arg0, Annotation[] arg1) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeFloatArray(float[] array, Annotation[] arg1) throws Exception {
        writeLen(array.length);
        if (array.length > 0) {
            var buf = new byte[array.length * Float.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRosMessagesConstants.ROS_BYTE_ORDER)
                    .asFloatBuffer()
                    .put(array);
            out.write(buf);
        }
    }
}
