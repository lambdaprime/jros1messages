/*
 * Copyright 2022 jrosclient project
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
package id.jros1messages.tests;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import id.jros1messages.Ros1MessageSerializationUtils;
import id.jros1messages.geometry_msgs.PolygonStampedMessage;
import id.jros1messages.sensor_msgs.JointStateMessage;
import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jros1messages.vision_msgs.ObjectHypothesisWithPoseMessage;
import id.jros1messages.visualization_msgs.MarkerMessage;
import id.jrosmessages.Message;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.PoseWithCovarianceMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.ResourceUtils;
import id.xfunction.XByte;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MessageTests {
    private static final ResourceUtils resourceUtils = new ResourceUtils();
    private Ros1MessageSerializationUtils serializationUtils = new Ros1MessageSerializationUtils();

    static Stream<List> dataProvider() {
        return Stream.of(
                // 1
                List.of(readResource("string-empty"), new StringMessage()),
                // 2
                List.of(readResource("string"), new StringMessage().withData("hello there")),
                // 3
                List.of(readResource("point-empty"), new PointMessage()),
                // 4
                List.of(readResource("point"), new PointMessage().withX(1.0).withY(1.0).withZ(1.0)),
                // 5
                List.of(
                        readResource("point32"),
                        new Point32Message().withX(1.0F).withY(1.0F).withZ(1.0F)),
                // 6
                List.of(readResource("quaternion-empty"), new QuaternionMessage()),
                // 7
                List.of(
                        readResource("quaternion"),
                        new QuaternionMessage().withX(1.0).withY(1.0).withZ(1.0).withW(3.0)),
                // 8
                List.of(readResource("pose-empty"), new PoseMessage()),
                // 9
                List.of(
                        readResource("pose"),
                        new PoseMessage()
                                .withPosition(new PointMessage().withX(1.0).withY(1.0).withZ(1.0))
                                .withQuaternion(
                                        new QuaternionMessage()
                                                .withX(1.0)
                                                .withY(1.0)
                                                .withZ(1.0)
                                                .withW(3.0))),
                // 10
                List.of(readResource("colorrgba-empty"), new ColorRGBAMessage()),
                // 11
                List.of(
                        readResource("colorrgba"),
                        new ColorRGBAMessage().withR(.12F).withG(.13F).withB(.14F).withA(.15F)),
                // 12
                List.of(readResource("vector3-empty"), new Vector3Message()),
                // 13
                List.of(
                        readResource("vector3"),
                        new Vector3Message().withX(.12).withY(.13).withZ(.14)),
                // 14
                List.of(
                        readResource("polygonstamped"),
                        new PolygonStampedMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withSeq(123)
                                                .withStamp(new Time(0, 1111))
                                                .withFrameId("aaaa"))
                                .withPolygon(
                                        new PolygonMessage()
                                                .withPoints(
                                                        new Point32Message[] {
                                                            new Point32Message(2F, 2F, 0F),
                                                            new Point32Message(1F, 2F, 3F),
                                                            new Point32Message(0F, 0F, 0F)
                                                        }))),
                // 15
                List.of(readResource("header-empty"), new HeaderMessage()),
                // 16
                List.of(
                        readResource("header"),
                        new HeaderMessage()
                                .withSeq(123)
                                .withStamp(new Time(0, 1111))
                                .withFrameId("aaaa")),
                // 17
                List.of(readResource("marker-empty"), new MarkerMessage()),
                // 18
                List.of(
                        readResource("marker"),
                        new MarkerMessage()
                                .withHeader(new HeaderMessage().withSeq(0).withFrameId("/map"))
                                .withNs(new StringMessage().withData("test"))
                                .withType(MarkerMessage.Type.CUBE)
                                .withAction(MarkerMessage.Action.ADD)
                                .withPose(
                                        new PoseMessage()
                                                .withPosition(
                                                        new PointMessage()
                                                                .withX(1.0)
                                                                .withY(1.0)
                                                                .withZ(1.0))
                                                .withQuaternion(
                                                        new QuaternionMessage()
                                                                .withX(1.0)
                                                                .withY(1.0)
                                                                .withZ(1.0)
                                                                .withW(3.0)))
                                .withScale(new Vector3Message().withX(1.0).withY(0.1).withZ(0.1))
                                .withColor(
                                        new ColorRGBAMessage()
                                                .withR(1.0F)
                                                .withG(0.13F)
                                                .withB(0.14F)
                                                .withA(1.0F))
                                .withText(new StringMessage().withData("aa"))
                                .withLifetime(new Duration())
                                .withFrameLocked(false)),
                // 19
                List.of(
                        readResource("pointcloud2"),
                        new PointCloud2Message()
                                .withHeader(
                                        new HeaderMessage()
                                                .withSeq(8)
                                                .withFrameId("map")
                                                .withStamp(new Time(1616650098, 493819000)))
                                .withHeight(1)
                                .withIsDense(true)
                                .withPointStep(12)
                                .withFields(
                                        new PointFieldMessage()
                                                .withName("x")
                                                .withOffset(0)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64),
                                        new PointFieldMessage()
                                                .withName("y")
                                                .withOffset(4)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64),
                                        new PointFieldMessage()
                                                .withName("z")
                                                .withOffset(8)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64))
                                .withData("a".repeat(96).getBytes())
                                .withRowStep(96)
                                .withWidth(8)),
                // 20
                List.of(
                        readResource("joint-state"),
                        new JointStateMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withSeq(43)
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withNames("joint_0", "joint_1", "joint_2", "joint_3", "joint_4")
                                .withPositions(
                                        new double[] {0.0, 0.0, 0.0, 0.767944870877505, 0.0})),
                // 23
                List.of(
                        readResource("obj_hypothesis"),
                        new ObjectHypothesisWithPoseMessage()
                                .withPose(
                                        new PoseWithCovarianceMessage()
                                                .withCovariance(
                                                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                                                        14, 15.56, 16, 17, 18, 19, 20, 21, 22, 23,
                                                        24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
                                                        35, 36))));
    }

    /** Read resource removing new lines if any */
    private static String readResource(String resourceName) {
        var resource =
                resourceUtils
                        .readResourceAsStream(MessageTests.class, resourceName)
                        .filter(s -> !s.isBlank())
                        .collect(joining(" "));
        return resource;
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testRead(List testData) throws Exception {
        Object expected = testData.get(1);
        Object actual =
                serializationUtils.read(
                        XByte.fromHexPairs((String) testData.get(0)),
                        (Class<? extends Message>) expected.getClass());
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testWrite(List testData) throws Exception {
        var b = (Message) testData.get(1);
        var out = serializationUtils.write(b);
        assertEquals(testData.get(0), XByte.toHexPairs(out));
    }
}
