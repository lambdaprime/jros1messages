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
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for object_recognition_msgs/TableArray */
@MessageMetadata(name = TableArrayMessage.NAME, md5sum = "cb3563a52bcb782c2c0d28e3e9a3dc50")
public class TableArrayMessage implements Message {

    static final String NAME = "object_recognition_msgs/TableArray";

    public HeaderMessage header = new HeaderMessage();

    /** Just an array of tables */
    public TableMessage[] tables = new TableMessage[0];

    public TableArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public TableArrayMessage withTables(TableMessage... tables) {
        this.tables = tables;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(tables));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (TableArrayMessage) obj;
        return Objects.equals(header, other.header) && Arrays.equals(tables, other.tables);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "tables", tables);
    }
}
