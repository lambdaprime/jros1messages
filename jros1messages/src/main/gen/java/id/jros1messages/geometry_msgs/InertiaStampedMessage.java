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
import id.jrosmessages.geometry_msgs.InertiaMessage;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for geometry_msgs/InertiaStamped */
@MessageMetadata(name = InertiaStampedMessage.NAME, md5sum = "f316819d435fac009022ead4726153cc")
public class InertiaStampedMessage implements Message {

    static final String NAME = "geometry_msgs/InertiaStamped";

    public HeaderMessage header = new HeaderMessage();

    public InertiaMessage inertia = new InertiaMessage();

    public InertiaStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public InertiaStampedMessage withInertia(InertiaMessage inertia) {
        this.inertia = inertia;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, inertia);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (InertiaStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(inertia, other.inertia);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "inertia", inertia);
    }
}
