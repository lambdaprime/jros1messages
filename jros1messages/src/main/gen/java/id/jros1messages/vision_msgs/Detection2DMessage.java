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

import id.jros1messages.sensor_msgs.ImageMessage;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for vision_msgs/Detection2D
 *
 * <p>Defines a 2D detection result.
 *
 * <p>This is similar to a 2D classification, but includes position information, allowing a
 * classification result for a specific crop or image point to to be located in the larger image.
 */
@MessageMetadata(
        name = Detection2DMessage.NAME,
        fields = {"header", "results", "bbox", "source_img"},
        md5sum = "9e11092151fa150724a255fbac727f3b")
public class Detection2DMessage implements Message {

    static final String NAME = "vision_msgs/Detection2D";

    public HeaderMessage header = new HeaderMessage();

    /** Class probabilities */
    public ObjectHypothesisWithPoseMessage[] results = new ObjectHypothesisWithPoseMessage[0];

    /** 2D bounding box surrounding the object. */
    public BoundingBox2DMessage bbox = new BoundingBox2DMessage();

    /**
     * The 2D data that generated these results (i.e. region proposal cropped out of the image). Not
     * required for all use cases, so it may be empty.
     */
    public ImageMessage source_img = new ImageMessage();

    public Detection2DMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Detection2DMessage withResults(ObjectHypothesisWithPoseMessage... results) {
        this.results = results;
        return this;
    }

    public Detection2DMessage withBbox(BoundingBox2DMessage bbox) {
        this.bbox = bbox;
        return this;
    }

    public Detection2DMessage withSourceImg(ImageMessage source_img) {
        this.source_img = source_img;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results), bbox, source_img);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Detection2DMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(results, other.results)
                && Objects.equals(bbox, other.bbox)
                && Objects.equals(source_img, other.source_img);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results,
                "bbox", bbox,
                "source_img", source_img);
    }
}
