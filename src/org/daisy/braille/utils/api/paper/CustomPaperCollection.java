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
package org.daisy.braille.utils.api.paper;

import java.io.IOException;
import java.util.Collection;

/**
 * Provides a custom paper collection that lets a user
 * add and remove papers. The collection is stored as
 * a file in the users home directory.
 */
public class CustomPaperCollection {
	private static CustomPaperCollection collection;

	private CustomPaperCollection() {
	}

	/**
	 * Gets the instance.
	 * @return returns the instance
	 */
	public synchronized static CustomPaperCollection getInstance() {
		if (collection==null) {
			collection = new CustomPaperCollection();
		}
		return collection;
	}

	/**
	 * Lists the papers in the collection.
	 * @return returns a collection of papers
	 */
	public synchronized Collection<Paper> list() {
		return UserPapersCollection.getInstance().list();
	}

	/**
	 * Adds a new sheet paper to the collection.
	 * @param name the name
	 * @param desc the description
	 * @param width the width
	 * @param height the height
	 * @return returns the new sheet paper
	 * @throws IOException if an I/O error occurs
	 */
	public synchronized SheetPaper addNewSheetPaper(String name, String desc, Length width, Length height) throws IOException {
		return UserPapersCollection.getInstance().addNewSheetPaper(name, desc, width, height);
	}

	/**
	 * Adds a new tractor paper to the collection.
	 * @param name the name
	 * @param desc the description
	 * @param across the length across the feed
	 * @param along the length along the feed
	 * @return returns the new tractor paper
	 * @throws IOException if an I/O error occurs
	 */
	public synchronized TractorPaper addNewTractorPaper(String name, String desc, Length across, Length along) throws IOException {
		return UserPapersCollection.getInstance().addNewTractorPaper(name, desc, across, along);
	}

	/**
	 * Adds a new roll paper to the collection.
	 * @param name the name
	 * @param desc the description
	 * @param across the length across the feed
	 * @return returns the new roll paper
	 * @throws IOException if an I/O error occurs
	 */
	public synchronized RollPaper addNewRollPaper(String name, String desc, Length across) throws IOException  {
		return UserPapersCollection.getInstance().addNewRollPaper(name, desc, across);
	}

	/**
	 * Removes the specified paper from the collection.
	 * @param p the paper to remove
	 * @throws IOException if an I/O error occurs
	 */
	public synchronized void remove(Paper p) throws IOException {
		UserPapersCollection.getInstance().remove(p);
	}

}