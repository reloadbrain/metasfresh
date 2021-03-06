package de.metas.document.refid.api.impl;

/*
 * #%L
 * de.metas.document.refid
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.adempiere.util.Check;

import de.metas.document.refid.api.IReferenceNoGeneratorInstance;
import de.metas.document.refid.model.I_C_ReferenceNo_Type;
import de.metas.document.refid.spi.IReferenceNoGenerator;

class ReferenceNoGeneratorInstance implements IReferenceNoGeneratorInstance
{
	private final I_C_ReferenceNo_Type type;
	private final List<Integer> assignedTableIds;
	private final IReferenceNoGenerator generator;

	public ReferenceNoGeneratorInstance(final I_C_ReferenceNo_Type type, final List<Integer> assignedTableIds, final IReferenceNoGenerator generator)
	{
		Check.assume(type != null, "type not null");
		Check.assume(assignedTableIds != null && !assignedTableIds.isEmpty(), "assignedTableIds not null and not empty");
		Check.assume(generator != null, "generator not null");

		this.type = type;
		this.assignedTableIds = Collections.unmodifiableList(new ArrayList<Integer>(assignedTableIds));
		this.generator = generator;
	}

	@Override
	public String generateReferenceNo(final Object sourceModel)
	{
		return generator.generateReferenceNo(sourceModel);
	}

	@Override
	public I_C_ReferenceNo_Type getType()
	{
		return type;
	}

	@Override
	public IReferenceNoGenerator getGenerator()
	{
		return generator;
	}

	@Override
	public List<Integer> getAssignedTableIds()
	{
		return assignedTableIds;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedTableIds == null) ? 0 : assignedTableIds.hashCode());
		result = prime * result + ((generator == null) ? 0 : generator.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		ReferenceNoGeneratorInstance other = (ReferenceNoGeneratorInstance)obj;
		if (assignedTableIds == null)
		{
			if (other.assignedTableIds != null)
			{
				return false;
			}
		}
		else if (!assignedTableIds.equals(other.assignedTableIds))
		{
			return false;
		}
		if (generator == null)
		{
			if (other.generator != null)
			{
				return false;
			}
		}
		else if (!generator.equals(other.generator))
		{
			return false;
		}
		if (type == null)
		{
			if (other.type != null)
			{
				return false;
			}
		}
		else if (!type.equals(other.type))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "ReferenceNoGeneratorInstance ["
				+ "type=" + (type == null ? null : type.getName())
				+ ", assignedTableIds=" + assignedTableIds
				+ ", generator=" + generator
				+ "]";
	}
}
