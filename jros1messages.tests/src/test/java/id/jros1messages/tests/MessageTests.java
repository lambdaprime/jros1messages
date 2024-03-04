/*
 * Copyright 2022 jrosclient project
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
package id.jros1messages.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import id.jros1messages.MessageSerializationUtils;
import id.jros1messages.geometry_msgs.PolygonStampedMessage;
import id.jros1messages.sensor_msgs.JointStateMessage;
import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jros1messages.visualization_msgs.MarkerMessage;
import id.jrosmessages.Message;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
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
    private MessageSerializationUtils serializationUtils = new MessageSerializationUtils();

    static Stream<List> dataProvider() {
        return Stream.of(
                // 1
                List.of(
                        resourceUtils.readResource(MessageTests.class, "string-empty"),
                        new StringMessage()),
                // 2
                List.of(
                        resourceUtils.readResource(MessageTests.class, "string"),
                        new StringMessage().withData("hello there")),
                // 3
                List.of(
                        resourceUtils.readResource(MessageTests.class, "point-empty"),
                        new PointMessage()),
                // 4
                List.of(
                        resourceUtils.readResource(MessageTests.class, "point"),
                        new PointMessage().withX(1.0).withY(1.0).withZ(1.0)),
                // 5
                List.of(
                        resourceUtils.readResource(MessageTests.class, "point32"),
                        new Point32Message().withX(1.0F).withY(1.0F).withZ(1.0F)),
                // 6
                List.of(
                        resourceUtils.readResource(MessageTests.class, "quaternion-empty"),
                        new QuaternionMessage()),
                // 7
                List.of(
                        resourceUtils.readResource(MessageTests.class, "quaternion"),
                        new QuaternionMessage().withX(1.0).withY(1.0).withZ(1.0).withW(3.0)),
                // 8
                List.of(
                        resourceUtils.readResource(MessageTests.class, "pose-empty"),
                        new PoseMessage()),
                // 9
                List.of(
                        resourceUtils.readResource(MessageTests.class, "pose"),
                        new PoseMessage()
                                .withPosition(new PointMessage().withX(1.0).withY(1.0).withZ(1.0))
                                .withQuaternion(
                                        new QuaternionMessage()
                                                .withX(1.0)
                                                .withY(1.0)
                                                .withZ(1.0)
                                                .withW(3.0))),
                // 10
                List.of(
                        resourceUtils.readResource(MessageTests.class, "colorrgba-empty"),
                        new ColorRGBAMessage()),
                // 11
                List.of(
                        resourceUtils.readResource(MessageTests.class, "colorrgba"),
                        new ColorRGBAMessage().withR(.12F).withG(.13F).withB(.14F).withA(.15F)),
                // 12
                List.of(
                        resourceUtils.readResource(MessageTests.class, "vector3-empty"),
                        new Vector3Message()),
                // 13
                List.of(
                        resourceUtils.readResource(MessageTests.class, "vector3"),
                        new Vector3Message().withX(.12).withY(.13).withZ(.14)),
                // 14
                List.of(
                        resourceUtils.readResource(MessageTests.class, "polygonstamped"),
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
                List.of(
                        resourceUtils.readResource(MessageTests.class, "header-empty"),
                        new HeaderMessage()),
                // 16
                List.of(
                        resourceUtils.readResource(MessageTests.class, "header"),
                        new HeaderMessage()
                                .withSeq(123)
                                .withStamp(new Time(0, 1111))
                                .withFrameId("aaaa")),
                // 17
                List.of(
                        resourceUtils.readResource(MessageTests.class, "marker-empty"),
                        new MarkerMessage()),
                // 18
                List.of(
                        resourceUtils.readResource(MessageTests.class, "marker"),
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
                        resourceUtils.readResource(MessageTests.class, "pointcloud2"),
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
                        resourceUtils.readResource(MessageTests.class, "joint-state"),
                        new JointStateMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withSeq(43)
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withNames("joint_0", "joint_1", "joint_2", "joint_3", "joint_4")
                                .withPositions(
                                        new double[] {0.0, 0.0, 0.0, 0.767944870877505, 0.0})));
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
