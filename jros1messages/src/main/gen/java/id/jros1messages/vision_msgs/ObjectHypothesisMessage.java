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
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/ObjectHypothesis
 *
 * <p>An object hypothesis that contains no position information.
 */
@MessageMetadata(
        name = ObjectHypothesisMessage.NAME,
        fields = {"id", "score"},
        md5sum = "abf73443e563396425a38201e9dacc73")
public class ObjectHypothesisMessage implements Message {

    static final String NAME = "vision_msgs/ObjectHypothesis";

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

    public ObjectHypothesisMessage withId(long id) {
        this.id = id;
        return this;
    }

    public ObjectHypothesisMessage withScore(double score) {
        this.score = score;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ObjectHypothesisMessage) obj;
        return Objects.equals(id, other.id) && score == other.score;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "id", id,
                "score", score);
    }
}
