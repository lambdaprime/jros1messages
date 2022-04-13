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
package id.jrosmessages.ros1.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import id.jrosmessages.MessageFormat;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.impl.RosDataInput;
import id.jrosmessages.impl.RosDataOutput;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.ros1.geometry_msgs.PolygonStampedMessage;
import id.jrosmessages.ros1.sensor_msgs.JointStateMessage;
import id.jrosmessages.ros1.sensor_msgs.PointCloud2Message;
import id.jrosmessages.ros1.std_msgs.HeaderMessage;
import id.jrosmessages.ros1.visualization_msgs.MarkerMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.xfunction.ResourceUtils;
import id.xfunction.io.XInputStream;
import id.xfunction.io.XOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MessageTests {
    private static final ResourceUtils resourceUtils = new ResourceUtils();

    static Stream<List> dataProvider() {
        return Stream.of(
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
                List.of(
                        resourceUtils.readResource(MessageTests.class, "header-empty"),
                        new HeaderMessage()),
                List.of(
                        resourceUtils.readResource(MessageTests.class, "header"),
                        new HeaderMessage()
                                .withSeq(123)
                                .withStamp(new Time(0, 1111))
                                .withFrameId("aaaa")),
                List.of(
                        resourceUtils.readResource(MessageTests.class, "marker-empty"),
                        new MarkerMessage()),
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
        var collector = new XInputStream((String) testData.get(0));
        var dis = new RosDataInput(new DataInputStream(collector));
        if (testData.size() == 3)
            dis = new RosDataInput(new DataInputStream(collector), (MessageFormat) testData.get(2));
        var ks = new KineticStreamReader(dis);
        Object expected = testData.get(1);
        Object actual = ks.read(expected.getClass());
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testWrite(List testData) throws Exception {
        var b = testData.get(1);
        var collector = new XOutputStream();
        var dos = new RosDataOutput(new DataOutputStream(collector));
        if (testData.size() == 3)
            dos =
                    new RosDataOutput(
                            new DataOutputStream(collector), (MessageFormat) testData.get(2));
        var ks = new KineticStreamWriter(dos);
        ks.write(b);
        assertEquals(testData.get(0), collector.asHexString());
    }
}
