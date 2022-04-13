/*
 * Copyright 2021 jrosclient project
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
package id.jrosmessages.ros1.object_recognition_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.object_recognition_msgs.RecognizedObjectMessage;
import id.jrosmessages.ros1.std_msgs.HeaderMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for object_recognition_msgs/RecognizedObjectArray */
@MessageMetadata(
        type = RecognizedObjectArrayMessage.NAME,
        md5sum = "5746f6dbdd0d9dca075766fb7c6dd65c")
public class RecognizedObjectArrayMessage implements Message {

    static final String NAME = "object_recognition_msgs/RecognizedObjectArray";

    /**
     * #################################################### HEADER
     * ###########################################################
     */
    @Streamed public HeaderMessage header = new HeaderMessage();

    /**
     * This message type describes a potential scene configuration: a set of objects that can
     * explain the scene
     */
    @Streamed public RecognizedObjectMessage[] objects = new RecognizedObjectMessage[0];

    /**
     * #################################################### SEARCH
     * ########################################################### The co-occurrence matrix between
     * the recognized objects
     */
    @Streamed public float[] cooccurrence = new float[0];

    public RecognizedObjectArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public RecognizedObjectArrayMessage withObjects(RecognizedObjectMessage... objects) {
        this.objects = objects;
        return this;
    }

    public RecognizedObjectArrayMessage withCooccurrence(float... cooccurrence) {
        this.cooccurrence = cooccurrence;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(objects), Arrays.hashCode(cooccurrence));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (RecognizedObjectArrayMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(objects, other.objects)
                && Arrays.equals(cooccurrence, other.cooccurrence);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "objects", objects,
                "cooccurrence", cooccurrence);
    }
}
