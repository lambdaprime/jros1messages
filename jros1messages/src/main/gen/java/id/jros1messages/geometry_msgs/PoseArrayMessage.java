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
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for geometry_msgs/PoseArray An array of poses with a header for global reference. */
@MessageMetadata(name = PoseArrayMessage.NAME, md5sum = "5f3f794301c7af61b3beab5b9997bb64")
public class PoseArrayMessage implements Message {

    static final String NAME = "geometry_msgs/PoseArray";

    public HeaderMessage header = new HeaderMessage();

    public PoseMessage[] poses = new PoseMessage[0];

    public PoseArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public PoseArrayMessage withPoses(PoseMessage... poses) {
        this.poses = poses;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(poses));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PoseArrayMessage) obj;
        return Objects.equals(header, other.header) && Arrays.equals(poses, other.poses);
    }

    @Override
    public String toString() {
        return XJson.asString("header", header, "poses", Arrays.toString(poses));
    }
}
