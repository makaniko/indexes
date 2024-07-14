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

import java.math.BigInteger;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NumberIndexesUtilTest {

    public static Stream<Arguments> indexConvertSmallNumbersData() {
        return Stream.of(Arguments.of("1-5,7,9-11", new int[]{1, 2, 3, 4, 5, 7, 9, 10, 11}),
                         Arguments.of("", new int[]{}),
                         Arguments.of("1,2,3,4,5", new int[]{1, 2, 3, 4, 5}),
                         Arguments.of("0-9", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}),
                         Arguments.of("0-0", new int[]{0}),
                         Arguments.of("1-0", new int[]{}),
                         Arguments.of("1,2,3,4,5", new int[]{1, 2, 3, 4, 5}));
    }

    public static Stream<Arguments> indexConvertBigNumbersData() {
        return Stream.of(Arguments.of("1000000000000000000000000000",
                                      new BigInteger[]{new BigInteger("1000000000000000000000000000", 10)}),
                         Arguments.of("1000000000000000000000000000-1000000000000000000000000001",
                                      new BigInteger[]{new BigInteger("1000000000000000000000000000", 10),
                                                       new BigInteger("1000000000000000000000000001", 10)}),
                         Arguments.of("1000000000000000000000000000,999999999999999999999999999",
                                      new BigInteger[]{new BigInteger("1000000000000000000000000000", 10),
                                                       new BigInteger("999999999999999999999999999", 10)}));
    }

    public static Stream<String> indexConvertIncorrectData() {
        return Stream.of("0.0", "0;0", "0:0", "0-A",
                         ",", "0,", ",0", "0-", "-0", "0,,0", "0--0", "0-0-0");
    }

    public static Stream<Arguments> indexesConvertSmallNumbersData() {
        return Stream.of(Arguments.of(new String[]{"1-5","7","9-11"},
                                      Stream.of(new int[]{1, 2, 3, 4, 5}, new int[]{7}, new int[]{9, 10, 11})
                                          .toArray(get2DimIntArrayGenerator())
                                     ));
    }

    @ParameterizedTest
    @MethodSource("indexConvertSmallNumbersData")
    public void testIndexConvert(String index, int[] expectedNumberIndex) {
        int[] actualNumberIndex = NumberIndexesUtil.convert(index);
        Assertions.assertArrayEquals(expectedNumberIndex, actualNumberIndex);
    }

    public static IntFunction<int[][]> get2DimIntArrayGenerator() {
        return len -> new int[len][];
    }
}
