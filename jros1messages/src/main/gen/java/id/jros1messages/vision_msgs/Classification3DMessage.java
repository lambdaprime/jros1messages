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
 * Definition for vision_msgs/Classification3D
 *
 * <p>Defines a 3D classification result.
 *
 * <p>This result does not contain any position information. It is designed for classifiers, which
 * simply provide probabilities given a source image.
 */
@MessageMetadata(
        name = Classification3DMessage.NAME,
        fields = {"header", "results", "source_cloud"},
        md5sum = "2c0fe97799b60ee2995363b3fbf44715")
public class Classification3DMessage implements Message {

    static final String NAME = "vision_msgs/Classification3D";

    public HeaderMessage header = new HeaderMessage();

    /** Class probabilities */
    public ObjectHypothesisMessage[] results = new ObjectHypothesisMessage[0];

    /**
     * The 3D data that generated these results (i.e. region proposal cropped out of the image). Not
     * required for all detectors, so it may be empty.
     */
    public PointCloud2Message source_cloud = new PointCloud2Message();

    public Classification3DMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Classification3DMessage withResults(ObjectHypothesisMessage... results) {
        this.results = results;
        return this;
    }

    public Classification3DMessage withSourceCloud(PointCloud2Message source_cloud) {
        this.source_cloud = source_cloud;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results), source_cloud);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Classification3DMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(results, other.results)
                && Objects.equals(source_cloud, other.source_cloud);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results,
                "source_cloud", source_cloud);
    }
}
