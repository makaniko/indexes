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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberIndexesUtil {
    public static BigInteger[][] convert(String[] indexes) {
        if (indexes == null) {
            throw new NullPointerException("String indexes are null");
        }
        BigInteger[][] numberIndexes = new BigInteger[indexes.length][];
        for (int i = 0; i < indexes.length; ++i) {
            numberIndexes[i] = convert(indexes[i]);
        }
        return numberIndexes;
    }

    public static BigInteger[] convert(String index) {
        if (index.length() == 0) {
            return new BigInteger[]{};
        }
        return Arrays.stream(index.split(","))
            .flatMap(NumberIndexesUtil::convertRange)
            .toArray(getBigIntArrayGenerator());
    }

    public static Stream<BigInteger> convertRange(String range) {
        try {
            if (index.length() == 0) {
                throw new IllegalArgumentException("Number sequence is empty");
            }
            int hyphenPos = range.indexOf('-');
            if (hyphenPos == -1) {
                BigInteger parsedNumber = new BigInteger(range);
                return Stream.of(parsedNumber);
            } else {
                BigInteger rangeFirstNumber = new BigInteger(range.substring(0, hyphenPos));
                BigInteger rangeLastNumber = new BigInteger(range.substring(hyphenPos + 1));
                ArrayList<BigInteger> numbers = new ArrayList<>();
                for (BigInteger i = rangeFirstNumber; i.compareTo(rangeLastNumber) != 1; i = i.add(BigInteger.ONE)) {
                    numbers.add(i);
                }
                return numbers.stream();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static BigInteger[][] getElementGroups(BigInteger[][] indexes) {
        BigInteger[][] distinctSortedIndexes = Arrays.stream(indexes)
            .map(index -> Arrays.stream(index).sorted().distinct()
                 .toArray(getBigIntArrayGenerator()))
            .toArray(len -> new BigInteger[len][]);
        int[] groupElementPos = new int[distinctSortedIndexes.length];
        ArrayList<BigInteger[]> elementGroups = new ArrayList<>();
        if (Stream.of(distinctSortedIndexes).anyMatch(arr -> arr.length == 0)) {
            return new BigInteger[0][];
        }
        for (int i = distinctSortedIndexes.length - 1; i >= 0;) {
            BigInteger[] elementGroup = new BigInteger[distinctSortedIndexes.length];
            for (int j = 0; j < distinctSortedIndexes.length; ++j) {
                elementGroup[j] = distinctSortedIndexes[j][groupElementPos[j]];
            }
            elementGroups.add(elementGroup);
            while (i >= 0 && groupElementPos[i] + 1 >= distinctSortedIndexes[i].length) {
                --i;
            }
            if (i >= 0) {
                ++groupElementPos[i];
                for (int k = i + 1; k < distinctSortedIndexes.length; ++k) {
                    groupElementPos[k] = 0;
                }
                i = distinctSortedIndexes.length - 1;
            }
        }
        return elementGroups.toArray(new BigInteger[0][]);
    }

    public static IntFunction<BigInteger[]> getBigIntArrayGenerator() {
        return len -> new BigInteger[len];
    }
}
