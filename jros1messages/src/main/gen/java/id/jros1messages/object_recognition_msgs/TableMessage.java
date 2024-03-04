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
package id.jros1messages.object_recognition_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for object_recognition_msgs/Table
 *
 * <p>Informs that a planar table has been detected at a given location
 */
@MessageMetadata(
        name = TableMessage.NAME,
        fields = {"header", "pose", "convex_hull"},
        md5sum = "39efebc7d51e44bd2d72f2df6c7823a2")
public class TableMessage implements Message {

    static final String NAME = "object_recognition_msgs/Table";

    public HeaderMessage header = new HeaderMessage();

    /**
     * The pose gives you the transform that take you to the coordinate system of the table, with
     * the origin somewhere in the table plane and the z axis normal to the plane
     */
    public PoseMessage pose = new PoseMessage();

    /**
     * There is no guarantee that the table does NOT extend further than the convex hull; this is
     * just as far as we've observed it. The origin of the table coordinate system is inside the
     * convex hull Set of points forming the convex hull of the table
     */
    public PointMessage[] convex_hull = new PointMessage[0];

    public TableMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public TableMessage withPose(PoseMessage pose) {
        this.pose = pose;
        return this;
    }

    public TableMessage withConvexHull(PointMessage... convex_hull) {
        this.convex_hull = convex_hull;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, pose, Arrays.hashCode(convex_hull));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (TableMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(pose, other.pose)
                && Arrays.equals(convex_hull, other.convex_hull);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "pose", pose,
                "convex_hull", convex_hull);
    }
}
