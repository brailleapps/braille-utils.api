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
public interface EmbosserWriterProperties extends EmbosserProperties {

	/**
	 * Gets the maximum row width in the current configuration
	 * @return returns the maximum row width, in characters
	 */
	public int getMaxWidth();

	/**
	 * Gets the maximum page height in the current configuration
	 * @return returns the maximum page height, in rows
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	public default int getMaxHeight() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns true if this embosser supports aligning. This indicates
	 * that rows can be padded with whitespace to move the text block
	 * horizontally using the value returned by <code>getMaxWidth</code>.
	 * Should return true for all physical embossers, since they all have
	 * a finite row length.
	 * @return returns true if this embosser supports aligning, false otherwise.
	 */
	public boolean supportsAligning();

	/**
	 * Returns true if this embosser has some method for volume handling
	 * @return returns true if this embosser supports volumes
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	//TODO: When removing these methods, don't forget to remove the inheritance from EmbosserProperties
	public default boolean supportsVolumes() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns true if this embosser supports 8 dot braille
	 * @return returns true if this embosser supports 8 dot braille
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	public default boolean supports8dot(){
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns true if this embosser supports duplex printing
	 * @return returns true if this embosser supports duplex printing
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	public default boolean supportsDuplex() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns true if this embosser supports z-folding. This indicates
	 * that, if tractor paper is used, the embosser can emboss every
	 * other paper upside down with the rear side up so that pages are
	 * ordered face up as they fold naturally in the output stack.
	 * @return returns true if this embosser supports z-folding, false otherwise.
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	public default boolean supportsZFolding() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns true if this embosser supports magazine layout. This indicates
	 * that the embosser can reorder pages and emboss two pages side-by-side
	 * on the same side of the paper (and two more on the other side), so that
	 * a readable document is created by stapling and folding the output stack
	 * in the middle.
	 * @param mode the print mode
	 * @return returns true if this embosser supports magazine layout, false otherwise.
	 * @deprecated Deprecated without replacement
	 */
	@Deprecated
	public default boolean supportsPrintMode(PrintMode mode) {
		throw new UnsupportedOperationException();
	}
}
