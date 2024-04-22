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

import id.jros1messages.geometry_msgs.PoseWithCovarianceStampedMessage;
import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.object_recognition_msgs.ObjectTypeMessage;
import id.jrosmessages.shape_msgs.MeshMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for object_recognition_msgs/RecognizedObject
 *
 * <p>#################################################### HEADER
 * ###########################################################
 */
@MessageMetadata(
        name = RecognizedObjectMessage.NAME,
        fields = {
            "header",
            "type",
            "confidence",
            "point_clouds",
            "bounding_mesh",
            "bounding_contours",
            "pose"
        },
        md5sum = "f92c4cb29ba11f26c5f7219de97e900d")
public class RecognizedObjectMessage implements Message {

    static final String NAME = "object_recognition_msgs/RecognizedObject";

    /** The header frame corresponds to the pose frame, NOT the point_cloud frame. */
    public HeaderMessage header = new HeaderMessage();

    /**
     * Contains information about the type and the position of a found object Some of those fields
     * might not be filled because the used techniques do not fill them or because the user does not
     * request them The type of the found object
     */
    public ObjectTypeMessage type = new ObjectTypeMessage();

    /**
     * confidence: how sure you are it is that object and not another one. It is between 0 and 1 and
     * the closer to one it is the better
     */
    public float confidence;

    /**
     * Sometimes you can extract the 3d points that belong to the object, in the frames of the
     * original sensors (it is an array as you might have several sensors)
     */
    public PointCloud2Message[] point_clouds = new PointCloud2Message[0];

    /**
     * Sometimes, you can only provide a bounding box/shape, even in 3d This is in the pose frame
     */
    public MeshMessage bounding_mesh = new MeshMessage();

    /**
     * Sometimes, you only have 2d input so you can't really give a pose, you just get a contour, or
     * a box The last point will be linked to the first one automatically
     */
    public PointMessage[] bounding_contours = new PointMessage[0];

    /**
     * This is the result that everybody expects : the pose in some frame given with the input. The
     * units are radian/meters as usual
     */
    public PoseWithCovarianceStampedMessage pose = new PoseWithCovarianceStampedMessage();

    public RecognizedObjectMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public RecognizedObjectMessage withType(ObjectTypeMessage type) {
        this.type = type;
        return this;
    }

    public RecognizedObjectMessage withConfidence(float confidence) {
        this.confidence = confidence;
        return this;
    }

    public RecognizedObjectMessage withPointClouds(PointCloud2Message... point_clouds) {
        this.point_clouds = point_clouds;
        return this;
    }

    public RecognizedObjectMessage withBoundingMesh(MeshMessage bounding_mesh) {
        this.bounding_mesh = bounding_mesh;
        return this;
    }

    public RecognizedObjectMessage withBoundingContours(PointMessage... bounding_contours) {
        this.bounding_contours = bounding_contours;
        return this;
    }

    public RecognizedObjectMessage withPose(PoseWithCovarianceStampedMessage pose) {
        this.pose = pose;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                type,
                confidence,
                Arrays.hashCode(point_clouds),
                bounding_mesh,
                Arrays.hashCode(bounding_contours),
                pose);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (RecognizedObjectMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(type, other.type)
                && confidence == other.confidence
                && Arrays.equals(point_clouds, other.point_clouds)
                && Objects.equals(bounding_mesh, other.bounding_mesh)
                && Arrays.equals(bounding_contours, other.bounding_contours)
                && Objects.equals(pose, other.pose);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "type", type,
                "confidence", confidence,
                "point_clouds", point_clouds,
                "bounding_mesh", bounding_mesh,
                "bounding_contours", bounding_contours,
                "pose", pose);
    }
}
