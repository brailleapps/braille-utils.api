/*
 * Braille Utils (C) 2010-2011 Daisy Consortium
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.daisy.braille.utils.api.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.daisy.braille.utils.api.factory.FactoryCatalog;
import org.daisy.braille.utils.api.factory.FactoryProperties;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Provides a catalog of Table factories.
 * @author Joel Håkansson
 */
@Component
public class TableCatalog implements FactoryCatalog<Table>, TableCatalogService {
	private final List<TableProvider> providers;
	private final Map<String, TableProvider> map;
	private final Logger logger;

	/**
	 * Creates a new empty instance. This method is public because it is required by OSGi.
	 * In an SPI context, use newInstance()
	 */
	public TableCatalog() {
		logger = Logger.getLogger(this.getClass().getCanonicalName());
		providers = new CopyOnWriteArrayList<>();
		map = Collections.synchronizedMap(new HashMap<String, TableProvider>());
		logger.warning("Note: braille-utils.api has been merged into dotify.api (https://github.com/brailleapps/dotify.api). " +
				"braille-utils.api will not be updated anymore. Please use dotify.api.");
	}

	/**
	 * <p>
	 * Creates a new TableCatalog and populates it using the SPI
	 * (java service provider interface).
	 * </p>
	 *
	 * <p>
	 * In an OSGi context, an instance should be retrieved using the service
	 * registry. It will be registered under the TableCatalogService
	 * interface.
	 * </p>
	 *
	 * @return returns a new TableCatalog
	 */
	public static TableCatalog newInstance() {
		TableCatalog ret = new TableCatalog();
		Iterator<TableProvider> i = ServiceLoader.load(TableProvider.class).iterator();
		while (i.hasNext()) {
			ret.addFactory(i.next());
		}
		return ret;
	}

	/**
	 * Adds a factory (intended for use by the OSGi framework)
	 * @param factory the factory to add
	 */
	@Reference(cardinality=ReferenceCardinality.MULTIPLE, policy=ReferencePolicy.DYNAMIC)
	public void addFactory(TableProvider factory) {
		logger.finer("Adding factory: " + factory);
		providers.add(factory);
	}

	/**
	 * Removes a factory (intended for use by the OSGi framework)
	 * @param factory the factory to remove
	 */
	// Unbind reference added automatically from addFactory annotation
	public void removeFactory(TableProvider factory) {
		// this is to avoid adding items to the cache that were removed while
		// iterating
		synchronized (map) {
			providers.remove(factory);
			map.clear();
		}
	}

	@Override
	public Table get(String identifier) {
		if (identifier==null) {
			return null;
		}
		TableProvider template = map.get(identifier);
		if (template==null) {
			// this is to avoid adding items to the cache that were removed
			// while iterating
			synchronized (map) {
				for (TableProvider p : providers) {
					for (FactoryProperties fp : p.list()) {
						if (fp.getIdentifier().equals(identifier)) {
							logger.fine("Found a factory for " + identifier + " (" + p.getClass() + ")");
							map.put(fp.getIdentifier(), p);
							template = p;
							break;
						}
					}
				}
			}
		}
		if (template!=null) {
			return template.newFactory(identifier);
		} else {
			return null;
		}
	}

	@Override
	public Table newTable(String identifier) {
		return get(identifier);
	}

	@Override
	public Collection<FactoryProperties> list() {
		Collection<FactoryProperties> ret = new ArrayList<>();
		for (TableProvider p : providers) {
			ret.addAll(p.list());
		}
		return ret;
	}

	@Override
	public Collection<FactoryProperties> list(TableFilter filter) {
		Collection<FactoryProperties> ret = new ArrayList<>();
		for (FactoryProperties fp : list()) {
			if (filter.accept(fp)) {
				ret.add(fp);
			}
		}
		return ret;
	}

}
