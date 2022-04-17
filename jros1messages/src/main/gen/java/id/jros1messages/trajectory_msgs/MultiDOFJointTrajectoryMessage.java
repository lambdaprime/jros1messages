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
package id.jros1messages.trajectory_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.trajectory_msgs.MultiDOFJointTrajectoryPointMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for trajectory_msgs/MultiDOFJointTrajectory */
@MessageMetadata(
        type = MultiDOFJointTrajectoryMessage.NAME,
        md5sum = "e19d4216caa321543b869ca5b1f5e224")
public class MultiDOFJointTrajectoryMessage implements Message {

    static final String NAME = "trajectory_msgs/MultiDOFJointTrajectory";

    /**
     * The header is used to specify the coordinate frame and the reference time for the trajectory
     * durations
     */
    @Streamed public HeaderMessage header = new HeaderMessage();

    /**
     * A representation of a multi-dof joint trajectory (each point is a transformation) Each point
     * along the trajectory will include an array of positions/velocities/accelerations that has the
     * same length as the array of joint names, and has the same order of joints as the joint names
     * array.
     */
    @Streamed public StringMessage[] joint_names = new StringMessage[0];

    @Streamed
    public MultiDOFJointTrajectoryPointMessage[] points =
            new MultiDOFJointTrajectoryPointMessage[0];

    public MultiDOFJointTrajectoryMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public MultiDOFJointTrajectoryMessage withJointNames(StringMessage... joint_names) {
        this.joint_names = joint_names;
        return this;
    }

    public MultiDOFJointTrajectoryMessage withPoints(
            MultiDOFJointTrajectoryPointMessage... points) {
        this.points = points;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(joint_names), Arrays.hashCode(points));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MultiDOFJointTrajectoryMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(joint_names, other.joint_names)
                && Arrays.equals(points, other.points);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "joint_names", joint_names,
                "points", points);
    }
}
