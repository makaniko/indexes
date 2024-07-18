/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.indexes;

import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortTest {
    @Test
    public void testConvertNullIndexes() {
        Port port = new Port();
        Assertions.assertThrows(NullPointerException.class, () -> port.convertToNumberIndexes());
    }

    @Test
    public void testConvertIndexes() {
        Port port = new Port();
        port.setIndexes(new String[]{"0,1-2", "0"});
        BigInteger[][] expectedNumberIndexes = new BigInteger[2][];
        expectedNumberIndexes[0] = new BigInteger[]{BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(2)};
        expectedNumberIndexes[1] = new BigInteger[]{BigInteger.ZERO};
        Assertions.assertArrayEquals(expectedNumberIndexes, port.convertToNumberIndexes());
    }

    @Test
    public void testElementGroups() {
        Port port = new Port();
        port.setIndexes(new String[]{"0,1-2", "0"});
        BigInteger[][] expectedElementGroups = new BigInteger[3][];
        expectedElementGroups[0] = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
        expectedElementGroups[1] = new BigInteger[]{BigInteger.ONE, BigInteger.ZERO};
        expectedElementGroups[2] = new BigInteger[]{BigInteger.valueOf(2), BigInteger.ZERO};
        Assertions.assertArrayEquals(expectedElementGroups, port.getIndexesElementGroups());
    }

    @Test
    public void testBothMethods() {
        Port port = new Port();
        port.setIndexes(new String[]{"0,1-2,3-4", "0-1"});
        BigInteger[][] numberIndexes = port.convertToNumberIndexes();
        Assertions.assertEquals(2, numberIndexes.length);
        BigInteger[][] elementGroups = port.getIndexesElementGroups();
        Assertions.assertEquals(10, elementGroups.length);
      
        port.setIndexes(new String[]{"0,0-2,2-3", "0-1,2-4"});
        elementGroups = port.getIndexesElementGroups();
        Assertions.assertEquals(20, elementGroups.length);
    }
}
