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
package com.indexes.util;

import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class NumberIndexesUtilTest {

    public static Stream<Object[]> indexConvertTestData() {
        return Stream.of(new Object[]{"1-5,7,9-11", new int[]{1, 2, 3, 4, 5, 7, 9, 10, 11}});
    }

    @ParameterizedTest
    @MethodSource("indexConvertTestData")
    public void testIndexConvert(String index, int[] expectedNumberIndex) {
        int[] actualNumberIndex = NumberIndexesUtil.convert(index);
        Assert.assertArrayEquals(expectedNumberIndex, actualNumberIndex);
    }
}
