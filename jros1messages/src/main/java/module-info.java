/*
 * Copyright 2022 jrosmessages project
 *
 * Website: https://github.com/lambdaprime/jrosmessages
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Java module with ROS1 (Robot Operating System) message definitions. These message definitions are
 * currently used in <a href="https://github.com/lambdaprime/jrosclient">jrosclient</a> but they are
 * independent from it and can be used in other Java projects too.
 *
 * <p>By default every message in this module implements toString method which returns ROS message
 * representation in JSON format (including all its values).
 *
 * @see <a href= "http://portal2.atwebpages.com/jrosclient/Defining_messages.html">Defining new
 *     messages</a>
 * @see <a href=
 *     "https://github.com/lambdaprime/jros1messages/blob/main/jros1messages/release/CHANGELOG.md">Download</a>
 * @see <a href="https://github.com/lambdaprime/jrosmessages">GitHub repository</a>
 * @see <a href= "http://portal2.atwebpages.com/jrosclient/">jrosclient documentation</a>
 * @author lambdaprime intid@protonmail.com
 */
module jros1messages {
    requires transitive jrosmessages;
    requires id.xfunction;
    requires id.kineticstreamer;
    requires java.logging;

    exports id.jros1messages;
    exports id.jros1messages.geometry_msgs;
    exports id.jros1messages.object_recognition_msgs;
    exports id.jros1messages.octomap_msgs;
    exports id.jros1messages.sensor_msgs;
    exports id.jros1messages.shape_msgs;
    exports id.jros1messages.std_msgs;
    exports id.jros1messages.trajectory_msgs;
    exports id.jros1messages.vision_msgs;
    exports id.jros1messages.visualization_msgs;
    exports id.jros1messages.impl to
            jros1messages.tests;
}
