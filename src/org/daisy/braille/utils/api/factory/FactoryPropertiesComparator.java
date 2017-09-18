package org.daisy.braille.utils.api.factory;

import java.util.Comparator;

/**
 * Provides a comparator for factory properties.
 *
 * Note: this comparator imposes orderings that are inconsistent with equals.
 *
 * @author Joel Håkansson
 *
 */
public class FactoryPropertiesComparator implements Comparator<FactoryProperties> {
	private Order order;
	private By by;

	/**
	 * Defines the sort order.
	 */
	public enum Order {
		/**
		 * Sort up.
		 */
		UP,
		/**
		 * Sort down.
		 */
		DOWN
	}

	/**
	 * Defines the item to sort by.
	 *
	 */
	public enum By {
		/**
		 * Sort by display name
		 */
		DISPLAY_NAME,
		/**
		 * Sort by identifier
		 */
		IDENTIFIER,
		/**
		 * Sort by description
		 */
		DESCRIPTION
	}

	/**
	 * Creates a new factory properties comparator.
	 */
	public FactoryPropertiesComparator() {
		this(Order.UP, By.DISPLAY_NAME);
	}

	private FactoryPropertiesComparator(Order order, By by) {
		this.order = order;
		this.by = by;
	}

	/**
	 * Sets the order.
	 * @param order the order
	 * @return returns this object
	 */
	public FactoryPropertiesComparator order(Order order) {
		this.order = order;
		return this;
	}

	/**
	 * Sets the type to order by.
	 * @param by the type to order by
	 * @return returns this object
	 */
	public FactoryPropertiesComparator by(By by) {
		this.by = by;
		return this;
	}

	@Override
	public int compare(FactoryProperties arg0, FactoryProperties arg1) {
		switch (by) {
		case DESCRIPTION:
			return (order==Order.UP?1:-1)*arg0.getDescription().compareTo(arg1.getDescription());
		case IDENTIFIER:
			return (order==Order.UP?1:-1)*arg0.getIdentifier().compareTo(arg1.getIdentifier());
		case DISPLAY_NAME: default:
			return (order==Order.UP?1:-1)*arg0.getDisplayName().compareTo(arg1.getDisplayName());
		}
	}

}
