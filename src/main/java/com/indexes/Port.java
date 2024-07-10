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

public class Port {
    private String[] indexes;
    private int[][] numberIndexes;

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }

    public int[][] convertToNumberIndexes() {
        this.numberIndexes = NumberIndexesUtil.convert(indexes);
        return numberIndexes;
    }

    public int[][] getIndexesElementGroups() {
        if (numberIndexes == null) {
            numberIndexes = convertToNumberIndexes(indexes);
        }
        return NumberIndexesUtil.getElementGroups(numberIndexes);
    }
}
