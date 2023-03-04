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
package id.jros1messages;

import id.jros1messages.impl.RosDataInput;
import id.jros1messages.impl.RosDataOutput;
import id.jrosmessages.Message;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.xfunction.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/** Performs message (de)serialization (from)to stream of bytes. */
public class MessageSerializationUtils {

    /**
     * Deserialize message from byte stream
     *
     * @param <M> type of the message
     * @param data byte array with the message
     * @param clazz message class
     */
    public <M extends Message> M read(byte[] data, Class<M> clazz) {
        Preconditions.isTrue(
                data.length != 0, "Could not read the message as there is no data to read");
        try {
            var dis = new DataInputStream(new ByteArrayInputStream(data));
            var ks = new KineticStreamReader(new RosDataInput(dis));
            Object obj = ks.read(clazz);
            return (M) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serialize message to byte stream
     *
     * @param message message to serialize
     */
    public byte[] write(Message message) {
        try {
            var bos = new ByteArrayOutputStream();
            var dos = new DataOutputStream(bos);
            var ks = new KineticStreamWriter(new RosDataOutput(dos));
            ks.write(message);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
