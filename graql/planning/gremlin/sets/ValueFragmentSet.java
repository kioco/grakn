/*
 * GRAKN.AI - THE KNOWLEDGE GRAPH
 * Copyright (C) 2019 Grakn Labs Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package grakn.core.graql.planning.gremlin.sets;

import com.google.common.collect.ImmutableSet;
import grakn.core.graql.planning.gremlin.fragment.Fragments;
import grakn.core.graql.planning.gremlin.value.ValueOperation;
import grakn.core.kb.graql.planning.gremlin.Fragment;
import graql.lang.property.VarProperty;
import graql.lang.statement.Variable;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Set;

class ValueFragmentSet extends EquivalentFragmentSetImpl {

    private final Variable var;
    private final ValueOperation<?, ?> operation;

    ValueFragmentSet(@Nullable VarProperty varProperty, Variable var, ValueOperation<?, ?> operation) {
        super(varProperty);
        this.var = var;
        this.operation = operation;
    }

    Variable var() {
        return var;
    }

    ValueOperation<?, ?> operation() {
        return operation;
    }

    @Override
    public final Set<Fragment> fragments() {
        return ImmutableSet.of(Fragments.value(varProperty(), var(), operation()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueFragmentSet that = (ValueFragmentSet) o;

        return (Objects.equals(this.varProperty(), that.varProperty()) &&
                this.var.equals(that.var()) &&
                this.operation.equals(that.operation()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(varProperty(), var, operation);
    }
}
