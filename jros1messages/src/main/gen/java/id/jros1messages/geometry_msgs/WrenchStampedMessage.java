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
package id.jros1messages.geometry_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.WrenchMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/WrenchStamped A wrench with reference coordinate frame and timestamp
 */
@MessageMetadata(type = WrenchStampedMessage.NAME, md5sum = "150502b356390fb151385ef7647f633e")
public class WrenchStampedMessage implements Message {

    static final String NAME = "geometry_msgs/WrenchStamped";

    @Streamed public HeaderMessage header = new HeaderMessage();

    @Streamed public WrenchMessage wrench = new WrenchMessage();

    public WrenchStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public WrenchStampedMessage withWrench(WrenchMessage wrench) {
        this.wrench = wrench;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, wrench);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (WrenchStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(wrench, other.wrench);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "wrench", wrench);
    }
}
