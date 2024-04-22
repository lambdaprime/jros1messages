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
package id.jros1messages.vision_msgs;

import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for vision_msgs/Detection3D
 *
 * <p>Defines a 3D detection result.
 *
 * <p>This extends a basic 3D classification by including position information, allowing a
 * classification result for a specific position in an image to to be located in the larger image.
 */
@MessageMetadata(
        name = Detection3DMessage.NAME,
        fields = {"header", "results", "bbox", "source_cloud"},
        md5sum = "7f3d8e29f3ab9853108801543aec1a5d")
public class Detection3DMessage implements Message {

    static final String NAME = "vision_msgs/Detection3D";

    public HeaderMessage header = new HeaderMessage();

    /**
     * Class probabilities. Does not have to include hypotheses for all possible object ids, the
     * scores for any ids not listed are assumed to be 0.
     */
    public ObjectHypothesisWithPoseMessage[] results = new ObjectHypothesisWithPoseMessage[0];

    /** 3D bounding box surrounding the object. */
    public BoundingBox3DMessage bbox = new BoundingBox3DMessage();

    /**
     * The 3D data that generated these results (i.e. region proposal cropped out of the image).
     * This information is not required for all detectors, so it may be empty.
     */
    public PointCloud2Message source_cloud = new PointCloud2Message();

    public Detection3DMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Detection3DMessage withResults(ObjectHypothesisWithPoseMessage... results) {
        this.results = results;
        return this;
    }

    public Detection3DMessage withBbox(BoundingBox3DMessage bbox) {
        this.bbox = bbox;
        return this;
    }

    public Detection3DMessage withSourceCloud(PointCloud2Message source_cloud) {
        this.source_cloud = source_cloud;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results), bbox, source_cloud);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Detection3DMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(results, other.results)
                && Objects.equals(bbox, other.bbox)
                && Objects.equals(source_cloud, other.source_cloud);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results,
                "bbox", bbox,
                "source_cloud", source_cloud);
    }
}
