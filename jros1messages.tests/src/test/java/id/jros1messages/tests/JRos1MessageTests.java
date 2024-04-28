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

import id.jros1messages.Ros1MessageSerializationUtils;
import id.jros1messages.geometry_msgs.PolygonStampedMessage;
import id.jros1messages.sensor_msgs.JointStateMessage;
import id.jros1messages.sensor_msgs.MultiDOFJointStateMessage;
import id.jros1messages.sensor_msgs.PointCloud2Message;
import id.jros1messages.std_msgs.HeaderMessage;
import id.jros1messages.trajectory_msgs.JointTrajectoryMessage;
import id.jros1messages.vision_msgs.ObjectHypothesisWithPoseMessage;
import id.jros1messages.visualization_msgs.MarkerArrayMessage;
import id.jros1messages.visualization_msgs.MarkerMessage;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.PoseWithCovarianceMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.geometry_msgs.TransformMessage;
import id.jrosmessages.geometry_msgs.TwistMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.geometry_msgs.WrenchMessage;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.tests.MessageTests;
import id.jrosmessages.trajectory_msgs.JointTrajectoryPointMessage;
import java.util.stream.Stream;

public class JRos1MessageTests extends MessageTests {

    public JRos1MessageTests() {
        super(new Ros1MessageSerializationUtils());
    }

    static Stream<TestCase> dataProvider() {
        return Stream.of(
                new TestCase("string-empty", new StringMessage()),
                /*
                rostopic pub testTopic std_msgs/String "hello there"
                 */
                new TestCase("string", new StringMessage().withData("hello there")),
                new TestCase("point-empty", new PointMessage()),
                /*
                rostopic pub testTopic geometry_msgs/Point "{x: 1.0, y: 1.0, z: 1.0}"
                 */
                new TestCase("point", new PointMessage().withX(1.0).withY(1.0).withZ(1.0)),
                new TestCase("point32", new Point32Message().withX(1.0F).withY(1.0F).withZ(1.0F)),
                new TestCase("quaternion-empty", new QuaternionMessage()),
                new TestCase(
                        "quaternion",
                        new QuaternionMessage().withX(1.0).withY(1.0).withZ(1.0).withW(3.0)),
                new TestCase("pose-empty", new PoseMessage()),
                /*
                rostopic pub testTopic geometry_msgs/Pose "{position: {x: 1.0, y: 1.0, z: 1.0}, orientation: {x: 1.0, y: 1.0, z: 1.0, w: 3.0}}"
                 */
                new TestCase(
                        "pose",
                        new PoseMessage()
                                .withPosition(new PointMessage().withX(1.0).withY(1.0).withZ(1.0))
                                .withQuaternion(
                                        new QuaternionMessage()
                                                .withX(1.0)
                                                .withY(1.0)
                                                .withZ(1.0)
                                                .withW(3.0))),
                new TestCase("colorrgba-empty", new ColorRGBAMessage()),
                /*
                rostopic pub testTopic std_msgs/ColorRGBA '{r: 0.12, g: 0.13, b: 0.14, a: 0.15}'
                 */
                new TestCase(
                        "colorrgba",
                        new ColorRGBAMessage().withR(.12F).withG(.13F).withB(.14F).withA(.15F)),
                new TestCase("vector3-empty", new Vector3Message()),
                /*
                rostopic pub testTopic geometry_msgs/Vector3 '{x: 0.12, y: 0.13, z: 0.14}'
                 */
                new TestCase("vector3", new Vector3Message().withX(.12).withY(.13).withZ(.14)),
                new TestCase(
                        "polygonstamped",
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
                new TestCase("header-empty", new HeaderMessage()),
                /*
                rostopic pub testTopic std_msgs/Header '{seq: 123, stamp: 1111, frame_id: "aaaa"}'
                 */
                new TestCase(
                        "header",
                        new HeaderMessage()
                                .withSeq(123)
                                .withStamp(new Time(0, 1111))
                                .withFrameId("aaaa")),
                new TestCase("marker-empty", new MarkerMessage()),
                /*
                rostopic pub testTopic visualization_msgs/Marker "{header: {seq: 123, stamp: 1111, frame_id: "aaaa"}, ns: "test", id: 123, type: 1, action: 0, pose: {position: {x: 1.0, y: 1.0, z: 1.0}, orientation: {x: 1.0, y: 1.0, z: 1.0, w: 3.0}}, scale: {x: 0.12, y: 0.13, z: 0.14}, color: {r: 0.12, g: 0.13, b: 0.14, a: 0.15}, lifetime: 1111, frame_locked: true}"
                 */
                new TestCase(
                        "marker-array",
                        new MarkerArrayMessage()
                                .withMarkers(
                                        new MarkerMessage()
                                                .withHeader(new HeaderMessage().withFrameId("/map"))
                                                .withNs(
                                                        new StringMessage()
                                                                .withData("basic_shapes"))
                                                .withType(MarkerMessage.Type.CUBE)
                                                .withAction(MarkerMessage.Action.ADD)
                                                .withPose(
                                                        new PoseMessage()
                                                                .withPosition(
                                                                        new PointMessage()
                                                                                .withX(1.0)
                                                                                .withY(0.0)
                                                                                .withZ(2.0))
                                                                .withQuaternion(
                                                                        new QuaternionMessage()
                                                                                .withX(0.0)
                                                                                .withY(0.0)
                                                                                .withZ(0.0)
                                                                                .withW(1.0)))
                                                .withScale(
                                                        new Vector3Message()
                                                                .withX(0.05)
                                                                .withY(0.05)
                                                                .withZ(0.05))
                                                .withColor(
                                                        new ColorRGBAMessage()
                                                                .withR(0.8F)
                                                                .withG(0.1F)
                                                                .withB(0.1F)
                                                                .withA(1.0F))
                                                .withText(new StringMessage().withData("aa"))
                                                .withLifetime(new Duration())
                                                .withFrameLocked(true)
                                                .withMeshUseEmbeddedMaterials(true))),
                new TestCase(
                        "pointcloud2",
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
                new TestCase(
                        "joint-state",
                        new JointStateMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withSeq(43)
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withNames("joint_0", "joint_1", "joint_2", "joint_3", "joint_4")
                                .withPositions(
                                        new double[] {0.0, 0.0, 0.0, 0.767944870877505, 0.0})),
                new TestCase(
                        "obj_hypothesis",
                        new ObjectHypothesisWithPoseMessage()
                                .withPose(
                                        new PoseWithCovarianceMessage()
                                                .withCovariance(
                                                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                                                        14, 15.56, 16, 17, 18, 19, 20, 21, 22, 23,
                                                        24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
                                                        35, 36))),
                /*
                 *
                rostopic pub -r 10 wrench "sensor_msgs/MultiDOFJointState" '
                header:
                  seq: 12
                  stamp: 0
                  frame_id: "aaa"
                joint_names: ["joint1", "joint2"]
                transforms:
                  - translation: [1,2,3]
                    rotation: [4,5,6,7]
                  - translation: [8,9,10]
                    rotation: [11,12,13,14]
                twist:
                  - linear: [11,12,13]
                    angular: [14,15,16]
                  - linear: [18,19,110]
                    angular: [111,112,113]
                wrench:
                  - force: [11,12,13]
                    torque: [14,15,16]
                  - force: [18,19,110]
                    torque: [111,112,113]
                '
                *
                */
                new TestCase(
                        "MultiDOFJointState",
                        new MultiDOFJointStateMessage()
                                .withHeader(new HeaderMessage().withSeq(12).withFrameId("aaa"))
                                .withJointNames(
                                        new StringMessage("joint1"), new StringMessage("joint2"))
                                .withTransforms(
                                        new TransformMessage()
                                                .withTranslation(new Vector3Message(1, 2, 3))
                                                .withRotation(
                                                        new QuaternionMessage(4., 5., 6., 7.)),
                                        new TransformMessage()
                                                .withTranslation(new Vector3Message(8, 9, 10))
                                                .withRotation(
                                                        new QuaternionMessage(11, 12, 13, 14)))
                                .withTwist(
                                        new TwistMessage()
                                                .withLinear(new Vector3Message(11, 12, 13))
                                                .withAngular(new Vector3Message(14, 15, 16)),
                                        new TwistMessage()
                                                .withLinear(new Vector3Message(18, 19, 110))
                                                .withAngular(new Vector3Message(111, 112, 113)))
                                .withWrench(
                                        new WrenchMessage()
                                                .withForce(new Vector3Message(11, 12, 13))
                                                .withTorque(new Vector3Message(14, 15, 16)),
                                        new WrenchMessage()
                                                .withForce(new Vector3Message(18, 19, 110))
                                                .withTorque(new Vector3Message(111, 112, 113)))),
                /*
                 *
                 rostopic pub -r 10 helloRos "trajectory_msgs/JointTrajectory" '
                 header:
                   stamp: [0, 123]
                   frame_id: "aaa"
                 joint_names: ["joint1", "joint2"]
                 points:
                   - positions: [1,2,3]
                     velocities: [4,5,6,7]
                     accelerations: [8,9,10]
                     effort: [11,12,13,14]
                     time_from_start: [333, 0]
                   - positions: [11,12,13]
                     velocities: [14,15,16,17]
                     accelerations: [18,19,10]
                     effort: [1,2,3,4]
                     time_from_start: [0, 54]
                 '
                *
                */
                new TestCase(
                        "JointTrajectory",
                        new JointTrajectoryMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(0, 123))
                                                .withFrameId("aaa"))
                                .withJointNames(
                                        new StringMessage("joint1"), new StringMessage("joint2"))
                                .withPoints(
                                        new JointTrajectoryPointMessage()
                                                .withPositions(1, 2, 3)
                                                .withVelocities(4, 5, 6, 7)
                                                .withAccelerations(8, 9, 10)
                                                .withEffort(11, 12, 13, 14)
                                                .withTimeFromStart(new Duration(333, 0)),
                                        new JointTrajectoryPointMessage()
                                                .withPositions(11, 12, 13)
                                                .withVelocities(14, 15, 16, 17)
                                                .withAccelerations(18, 19, 10)
                                                .withEffort(1, 2, 3, 4)
                                                .withTimeFromStart(new Duration(0, 54)))));
    }
}
