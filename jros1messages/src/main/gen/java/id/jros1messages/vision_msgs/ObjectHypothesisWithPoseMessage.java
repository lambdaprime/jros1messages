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
package id.jros1messages.vision_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PoseWithCovarianceMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/ObjectHypothesisWithPose
 *
 * <p>An object hypothesis that contains position information.
 */
@MessageMetadata(
        name = ObjectHypothesisWithPoseMessage.NAME,
        fields = {"id", "score", "pose"},
        md5sum = "fa1ab3bc7146f53921fa142d631d02db")
public class ObjectHypothesisWithPoseMessage implements Message {

    static final String NAME = "vision_msgs/ObjectHypothesisWithPose";

    /**
     * The unique numeric ID of object detected. To get additional information about this ID, such
     * as its human-readable name, listeners should perform a lookup in a metadata database. See
     * vision_msgs/VisionInfo.msg for more detail.
     */
    public long id;

    /**
     * The probability or confidence value of the detected object. By convention, this value should
     * lie in the range [0-1].
     */
    public double score;

    /**
     * The 6D pose of the object hypothesis. This pose should be defined as the pose of some fixed
     * reference point on the object, such a the geometric center of the bounding box or the center
     * of mass of the object. Note that this pose is not stamped; frame information can be defined
     * by parent messages. Also note that different classes predicted for the same input data may
     * have different predicted 6D poses.
     */
    public PoseWithCovarianceMessage pose = new PoseWithCovarianceMessage();

    public ObjectHypothesisWithPoseMessage withId(long id) {
        this.id = id;
        return this;
    }

    public ObjectHypothesisWithPoseMessage withScore(double score) {
        this.score = score;
        return this;
    }

    public ObjectHypothesisWithPoseMessage withPose(PoseWithCovarianceMessage pose) {
        this.pose = pose;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, pose);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ObjectHypothesisWithPoseMessage) obj;
        return Objects.equals(id, other.id)
                && score == other.score
                && Objects.equals(pose, other.pose);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "id", id,
                "score", score,
                "pose", pose);
    }
}
