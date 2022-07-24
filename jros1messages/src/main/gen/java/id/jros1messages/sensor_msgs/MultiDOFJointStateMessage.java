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
package id.jros1messages.sensor_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.TransformMessage;
import id.jrosmessages.geometry_msgs.TwistMessage;
import id.jrosmessages.geometry_msgs.WrenchMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for sensor_msgs/MultiDOFJointState Representation of state for joints with multiple
 * degrees of freedom, # following the structure of JointState. # # It is assumed that a joint in a
 * system corresponds to a transform that gets applied # along the kinematic chain. For example, a
 * planar joint (as in URDF) is 3DOF (x, y, yaw) # and those 3DOF can be expressed as a
 * transformation matrix, and that transformation # matrix can be converted back to (x, y, yaw) # #
 * Each joint is uniquely identified by its name # The header specifies the time at which the joint
 * states were recorded. All the joint states # in one message have to be recorded at the same time.
 * # # This message consists of a multiple arrays, one for each part of the joint state. # The goal
 * is to make each of the fields optional. When e.g. your joints have no # wrench associated with
 * them, you can leave the wrench array empty. # # All arrays in this message should have the same
 * size, or be empty. # This is the only way to uniquely associate the joint name with the correct #
 * states.
 */
@MessageMetadata(name = MultiDOFJointStateMessage.NAME, md5sum = "ca6307bcc53f56ce3e190e089129c7f6")
public class MultiDOFJointStateMessage implements Message {

    static final String NAME = "sensor_msgs/MultiDOFJointState";

    public HeaderMessage header = new HeaderMessage();

    public StringMessage[] joint_names = new StringMessage[0];

    public TransformMessage[] transforms = new TransformMessage[0];

    public TwistMessage[] twist = new TwistMessage[0];

    public WrenchMessage[] wrench = new WrenchMessage[0];

    public MultiDOFJointStateMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public MultiDOFJointStateMessage withJointNames(StringMessage... joint_names) {
        this.joint_names = joint_names;
        return this;
    }

    public MultiDOFJointStateMessage withTransforms(TransformMessage... transforms) {
        this.transforms = transforms;
        return this;
    }

    public MultiDOFJointStateMessage withTwist(TwistMessage... twist) {
        this.twist = twist;
        return this;
    }

    public MultiDOFJointStateMessage withWrench(WrenchMessage... wrench) {
        this.wrench = wrench;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                Arrays.hashCode(joint_names),
                Arrays.hashCode(transforms),
                Arrays.hashCode(twist),
                Arrays.hashCode(wrench));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MultiDOFJointStateMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(joint_names, other.joint_names)
                && Arrays.equals(transforms, other.transforms)
                && Arrays.equals(twist, other.twist)
                && Arrays.equals(wrench, other.wrench);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "joint_names", joint_names,
                "transforms", transforms,
                "twist", twist,
                "wrench", wrench);
    }
}
