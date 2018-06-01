package de.metas.purchasecandidate.availability;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.adempiere.util.Check;
import org.adempiere.util.GuavaCollectors;

import com.google.common.collect.ImmutableMap;

import de.metas.purchasecandidate.PurchaseCandidateGroup;
import de.metas.vendor.gateway.api.availability.TrackingId;
import lombok.Value;

/*
 * #%L
 * de.metas.purchasecandidate.base
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Value
public class PurchaseCandidatesAvailabilityRequest
{
	public static final PurchaseCandidatesAvailabilityRequest of(final Map<TrackingId, PurchaseCandidateGroup> purchaseCandidateGroups)
	{
		return new PurchaseCandidatesAvailabilityRequest(purchaseCandidateGroups);
	}

	public static final PurchaseCandidatesAvailabilityRequest of(final Collection<PurchaseCandidateGroup> purchaseCandidateGroups)
	{
		return new PurchaseCandidatesAvailabilityRequest(purchaseCandidateGroups.stream()
				.collect(GuavaCollectors.toImmutableMapByKey(purchaseCandidateGroup -> TrackingId.random())));
	}

	ImmutableMap<TrackingId, PurchaseCandidateGroup> purchaseCandidateGroups;

	private PurchaseCandidatesAvailabilityRequest(final Map<TrackingId, PurchaseCandidateGroup> purchaseCandidateGroups)
	{
		Check.assumeNotEmpty(purchaseCandidateGroups, "purchaseCandidateGroups is not empty");

		this.purchaseCandidateGroups = ImmutableMap.copyOf(purchaseCandidateGroups);
	}

	public Set<TrackingId> getTrackingIds()
	{
		return purchaseCandidateGroups.keySet();
	}

}
