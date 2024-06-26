/*
 * Copyright 2021 jrosclient project
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
package id.jros1messages.object_recognition_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for object_recognition_msgs/RecognizedObjectArray */
@MessageMetadata(
        name = RecognizedObjectArrayMessage.NAME,
        fields = {"header", "objects", "cooccurrence"},
        md5sum = "bad6b1546b9ebcabb49fb3b858d78964")
public class RecognizedObjectArrayMessage implements Message {

    static final String NAME = "object_recognition_msgs/RecognizedObjectArray";

    /**
     * #################################################### HEADER
     * ###########################################################
     */
    public HeaderMessage header = new HeaderMessage();

    /**
     * This message type describes a potential scene configuration: a set of objects that can
     * explain the scene
     */
    public RecognizedObjectMessage[] objects = new RecognizedObjectMessage[0];

    /**
     * #################################################### SEARCH
     * ########################################################### The co-occurrence matrix between
     * the recognized objects
     */
    public float[] cooccurrence = new float[0];

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
