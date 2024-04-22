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
package id.jros1messages.shape_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for shape_msgs/SolidPrimitive
 *
 * <p>Define box, sphere, cylinder, cone All shapes are defined to have their bounding boxes
 * centered around 0,0,0.
 */
@MessageMetadata(
        name = SolidPrimitiveMessage.NAME,
        fields = {"BOX", "SPHERE", "CYLINDER", "CONE", "type", "dimensions"},
        md5sum = "d8f8cbc74c5ff283fca29569ccefb45d")
public class SolidPrimitiveMessage implements Message {

    static final String NAME = "shape_msgs/SolidPrimitive";

    public enum ShapeType {
        BOX,
        SPHERE,
        CYLINDER,
        CONE
    }

    public enum BoxDimensionType {
        /**
         * The meaning of the shape dimensions: each constant defines the index in the 'dimensions'
         * array For the BOX type, the X, Y, and Z dimensions are the length of the corresponding
         * sides of the box.
         */
        BOX_X,

        BOX_Y,

        BOX_Z,
    }

    public enum SphereDimensionType {
        /**
         * For the SPHERE type, only one component is used, and it gives the radius of the sphere.
         */
        SPHERE_RADIUS,
    }

    public enum CylinderDimensionType {
        /**
         * For the CYLINDER and CONE types, the center line is oriented along the Z axis. Therefore
         * the CYLINDER_HEIGHT (CONE_HEIGHT) component of dimensions gives the height of the
         * cylinder (cone). The CYLINDER_RADIUS (CONE_RADIUS) component of dimensions gives the
         * radius of the base of the cylinder (cone). Cone and cylinder primitives are defined to be
         * circular. The tip of the cone is pointing up, along +Z axis.
         */
        CYLINDER_HEIGHT,

        CYLINDER_RADIUS,
    }

    public enum ConeDimensionType {
        CONE_HEIGHT,

        CONE_RADIUS,
    }

    /** The type of the shape */
    public byte type;

    /** The dimensions of the shape */
    public double[] dimensions = new double[0];

    public SolidPrimitiveMessage withShapeType(ShapeType type) {
        // ROS enumeration starts from 1
        this.type = (byte) (type.ordinal() + 1);
        return this;
    }

    public SolidPrimitiveMessage withDimensions(double... dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, Arrays.hashCode(dimensions));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (SolidPrimitiveMessage) obj;
        return type == other.type && Arrays.equals(dimensions, other.dimensions);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "type", type,
                "dimensions", dimensions);
    }
}
