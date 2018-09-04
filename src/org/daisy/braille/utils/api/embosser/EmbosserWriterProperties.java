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
package org.daisy.braille.utils.api.embosser;

/**
 * Provides information about the embosser.
 * @author Joel Håkansson
 */
public interface EmbosserWriterProperties {

	/**
	 * Gets the maximum row width in the current configuration
	 * @return returns the maximum row width, in characters
	 */
	public int getMaxWidth();

	/**
	 * Returns true if this embosser supports aligning. This indicates
	 * that rows can be padded with whitespace to move the text block
	 * horizontally using the value returned by <code>getMaxWidth</code>.
	 * Should return true for all physical embossers, since they all have
	 * a finite row length.
	 * @return returns true if this embosser supports aligning, false otherwise.
	 */
	public boolean supportsAligning();

}
