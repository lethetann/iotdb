/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.mpp.plan.expression.visitor;

import org.apache.iotdb.db.mpp.plan.expression.Expression;
import org.apache.iotdb.db.mpp.plan.expression.leaf.LeafOperand;
import org.apache.iotdb.db.mpp.plan.expression.multi.FunctionExpression;

import java.util.Collections;
import java.util.List;

public class CollectAggregationExpressionsVisitor extends CollectVisitor {
  @Override
  public List<Expression> visitFunctionExpression(
      FunctionExpression functionExpression, Void context) {
    if (functionExpression.isBuiltInAggregationFunctionExpression()) {
      return Collections.singletonList(functionExpression);
    }
    return mergeList(getResultsFromChild(functionExpression, null));
  }

  @Override
  public List<Expression> visitLeafOperand(LeafOperand leafOperand, Void context) {
    return Collections.emptyList();
  }
}
