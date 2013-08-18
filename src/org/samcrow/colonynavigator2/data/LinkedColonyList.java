package org.samcrow.colonynavigator2.data;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Linked-list implementation of a colony list
 * @author Sam Crow
 *
 */
public class LinkedColonyList extends LinkedList<Colony> implements ColonyList {

	private static final long serialVersionUID = 2850869753338414340L;

	public LinkedColonyList() {
	}

	public LinkedColonyList(Collection<? extends Colony> collection) {
		super(collection);
	}

	/**
	 * A simple implementation that iterates over the colonies in list order
	 * @see ColonyList#getById(int)
	 */
	@Override
	public Colony getById(int colonyId) {
		for(Colony colony : this) {
			if(colony.getId() == colonyId) {
				return colony;
			}
		}
		
		return null;
	}

}
