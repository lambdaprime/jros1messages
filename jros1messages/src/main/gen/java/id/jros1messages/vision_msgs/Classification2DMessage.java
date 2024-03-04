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
 * Definition for vision_msgs/Classification2D
 *
 * <p>Defines a 2D classification result.
 *
 * <p>This result does not contain any position information. It is designed for classifiers, which
 * simply provide class probabilities given a source image.
 */
@MessageMetadata(
        name = Classification2DMessage.NAME,
        fields = {"header", "results", "source_img"},
        md5sum = "b23d0855d0f41568e09106615351255f")
public class Classification2DMessage implements Message {

    static final String NAME = "vision_msgs/Classification2D";

    public HeaderMessage header = new HeaderMessage();

    /**
     * A list of class probabilities. This list need not provide a probability for every possible
     * class, just ones that are nonzero, or above some user-defined threshold.
     */
    public ObjectHypothesisMessage[] results = new ObjectHypothesisMessage[0];

    /**
     * The 2D data that generated these results (i.e. region proposal cropped out of the image). Not
     * required for all use cases, so it may be empty.
     */
    public ImageMessage source_img = new ImageMessage();

    public Classification2DMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Classification2DMessage withResults(ObjectHypothesisMessage... results) {
        this.results = results;
        return this;
    }

    public Classification2DMessage withSourceImg(ImageMessage source_img) {
        this.source_img = source_img;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results), source_img);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Classification2DMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(results, other.results)
                && Objects.equals(source_img, other.source_img);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results,
                "source_img", source_img);
    }
}
