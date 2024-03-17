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
import id.jrosmessages.MessageMetadataAccessor;
import id.kineticstreamer.KineticStreamController;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.kineticstreamer.PublicStreamedFieldsProvider;
import id.kineticstreamer.StreamedFieldsProvider;
import id.xfunction.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;

/** Performs message (de)serialization (from)to stream of bytes. Must be thread-safe. */
public class MessageSerializationUtils {
    private static final MessageMetadataAccessor METADATA_ACCESSOR = new MessageMetadataAccessor();
    private static final StreamedFieldsProvider FIELDS_PROVIDER =
            new PublicStreamedFieldsProvider(
                    clazz -> METADATA_ACCESSOR.getFields((Class<Message>) clazz));

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
            var buf = ByteBuffer.wrap(data);
            var ks =
                    new KineticStreamReader(new RosDataInput(buf))
                            .withController(
                                    new KineticStreamController()
                                            .withFieldsProvider(FIELDS_PROVIDER));
            Object obj = ks.read(clazz);
            return (M) obj;
        } catch (Exception e) {
            throw new RuntimeException("Problem reading " + clazz.getName(), e);
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
            var controller = new KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
            var ks =
                    new KineticStreamWriter(new RosDataOutput(dos, controller))
                            .withController(controller);
            ks.write(message);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Problem writing " + message.getClass().getName(), e);
        }
    }
}
