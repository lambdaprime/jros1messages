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
package id.jros1messages.geometry_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/QuaternionStamped
 *
 * <p>This represents an orientation with reference coordinate frame and timestamp. This represents
 * an orientation with reference coordinate frame and timestamp.
 */
@MessageMetadata(
        name = QuaternionStampedMessage.NAME,
        fields = {"header", "quaternion"},
        md5sum = "e57f1e547e0e1fd13504588ffc8334e2")
public class QuaternionStampedMessage implements Message {

    static final String NAME = "geometry_msgs/QuaternionStamped";

    public HeaderMessage header = new HeaderMessage();

    public QuaternionMessage quaternion = new QuaternionMessage();

    public QuaternionStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public QuaternionStampedMessage withQuaternion(QuaternionMessage quaternion) {
        this.quaternion = quaternion;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, quaternion);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (QuaternionStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(quaternion, other.quaternion);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "quaternion", quaternion);
    }
}
