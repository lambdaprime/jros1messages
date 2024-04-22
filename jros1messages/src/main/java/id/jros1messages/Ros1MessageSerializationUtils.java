/*
 * Copyright 2022 jrosclient project
 * 
 * Website: https://github.com/lambdaprime/jros1messages
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

import id.jros1messages.impl.Ros1DataInput;
import id.jros1messages.impl.Ros1DataOutput;
import id.jrosmessages.impl.AbstractMessageSerializationUtils;
import id.kineticstreamer.KineticStreamController;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.xfunction.logging.TracingToken;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/** Performs message (de)serialization (from)to stream of bytes. Must be thread-safe. */
public class Ros1MessageSerializationUtils extends AbstractMessageSerializationUtils {
    private static final TracingToken TRACING_TOKEN =
            new TracingToken(Ros1MessageSerializationUtils.class.getSimpleName());

    public Ros1MessageSerializationUtils() {
        super(TRACING_TOKEN, Map.of("RosVersion", "ROS1"));
    }

    @Override
    protected KineticStreamReader newKineticStreamReader(ByteBuffer buf) {
        var controller = new KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
        return new KineticStreamReader(new Ros1DataInput(buf, controller))
                .withController(controller);
    }

    @Override
    protected KineticStreamWriter newKineticStreamWriter(DataOutputStream dos) {
        var controller = new KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
        return new KineticStreamWriter(new Ros1DataOutput(dos, controller))
                .withController(controller);
    }
}
