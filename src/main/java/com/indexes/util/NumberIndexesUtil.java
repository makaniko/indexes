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

import java.util.Arrays;
import java.util.stream.IntStream;

public class NumberIndexesUtil {
    public static int[][] convert(String[] indexes) {
        if (indexes == null) {
            throw new NullPointerException("String indexes are null");
        }
        int[][] numberIndexes = new int[indexes.length][];
        for (int i = 0; i < indexes.length; ++i) {
            numberIndexes[i] = convert(indexes[i]);
        }
        return numberIndexes;
    }

    public static int[] convert(String index) {
        return Arrays.stream(index.split(","))
            .flatMapToInt(NumberIndexesUtil::convertRange)
            .toArray();
    }

    public static IntStream convertRange(String range) {
        int hyphenPos = range.indexOf('-');
        if (hyphenPos == -1) {
            return IntStream.of(Integer.parseInt(range));
        } else {
            int rangeFirstNumber = Integer.parseInt(range.substring(0, hyphenPos));
            int rangeLastNumber = Integer.parseInt(range.substring(hyphenPos + 1));
            return IntStream.iterate(rangeFirstNumber, n -> n+ 1)
                .limit(rangeLastNumber - rangeFirstNumber + 1);
        }
    }

    public static int[][] getElementGroups(int[][] indexes) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
