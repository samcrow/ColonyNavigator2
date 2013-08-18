package org.samcrow.colonynavigator2.data;

import java.util.List;
import java.util.Set;

/**
 * An interface for a list of colonies
 * @author Sam Crow
 *
 */
public interface ColonyList extends List<Colony>, Set<Colony> {
	
	/**
	 * Returns a colony with the given ID, or null if no colony
	 * with that ID is available
	 * @param colonyId
	 * @return
	 */
	public Colony getById(int colonyId);

}
