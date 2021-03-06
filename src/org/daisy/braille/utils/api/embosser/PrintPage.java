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

import org.daisy.braille.utils.api.embosser.EmbosserProperties.PrintMode;
import org.daisy.braille.utils.api.paper.Dimensions;
import org.daisy.braille.utils.api.paper.Length;
import org.daisy.braille.utils.api.paper.PageFormat;
import org.daisy.braille.utils.api.paper.RollPaperFormat;
import org.daisy.braille.utils.api.paper.SheetPaperFormat;
import org.daisy.braille.utils.api.paper.TractorPaperFormat;

/**
 *
 * @author Bert Frees
 * @author Joel Håkansson
 */
public class PrintPage implements Dimensions {

	/**
	 *  Direction of print
	 */
	public enum PrintDirection {
		/**
		 *  Direction of embosser head is equal to direction of feeding paper
		 */
		UPRIGHT,
		/**
		 *  Direction of embosser head is opposite to direction of feeding paper
		 */
		SIDEWAYS
	}

	/**
	 * The shape of the paper
	 */
	public enum Shape {
		/**
		 *  Represents portrait shape, that is to say that getWidth()&lt;getHeight()
		 */
		PORTRAIT,
		/**
		 *  Represents landscape shape, that is to say that getWidth&gt;getHeight()
		 */
		LANDSCAPE,
		/**
		 *  Represents square shape, that is to say that getWidth()==getHeight()
		 */
		SQUARE
	}

	private final PageFormat inputPage;
	private final PrintDirection direction;
	private final PrintMode mode;

	/**
	 * Creates a new print page with the specified parameters.
	 * @param inputPage the page format
	 * @param direction the print direction
	 * @param mode the print mode
	 */
	public PrintPage(PageFormat inputPage, PrintDirection direction, PrintMode mode) {
		this.inputPage = inputPage;
		this.direction = direction;
		this.mode = mode;
	}

	/**
	 * Creates a new print page with the specified page format
	 * and default print direction and print mode.
	 * @param inputPage the page format
	 */
	public PrintPage(PageFormat inputPage) {
		this(inputPage, PrintDirection.UPRIGHT, PrintMode.REGULAR);
	}

	/**
	 * Gets the length of the paper perpendicular to the direction of the paper feed.
	 * @return returns the length.
	 */
	public Length getLengthAcrossFeed() {
		switch (inputPage.getPageFormatType()) {
		case SHEET: {
			switch (direction) {
			case SIDEWAYS:
				return ((SheetPaperFormat)inputPage).getPageHeight();
			case UPRIGHT: default:
				return ((SheetPaperFormat)inputPage).getPageWidth();
			}
		}
		case ROLL:
			return ((RollPaperFormat)inputPage).getLengthAcrossFeed();
		case TRACTOR:
			return ((TractorPaperFormat)inputPage).getLengthAcrossFeed();
		default:
			throw new RuntimeException("Coding error");
		}
	}

	/**
	 * Gets the length of the paper along the direction of the paper feed
	 * @return returns the length.
	 */
	public Length getLengthAlongFeed() {
		switch (inputPage.getPageFormatType()) {
		case SHEET: {
			switch (direction) {
			case SIDEWAYS:
				return ((SheetPaperFormat)inputPage).getPageWidth();
			case UPRIGHT: default:
				return ((SheetPaperFormat)inputPage).getPageHeight();
			}
		}
		case ROLL:
			return ((RollPaperFormat)inputPage).getLengthAlongFeed();
		case TRACTOR:
			return ((TractorPaperFormat)inputPage).getLengthAlongFeed();
		default:
			throw new RuntimeException("Coding error");
		}
	}

	@Override
	public double getWidth() {
		double width;

		switch (direction) {
		case SIDEWAYS:
			width = getLengthAlongFeed().asMillimeter();
			break;
		case UPRIGHT:
		default:
			width = getLengthAcrossFeed().asMillimeter();
		}

		switch (mode) {
		case MAGAZINE:
			return width/2;
		case REGULAR:
		default:
			return width;
		}
	}

	@Override
	public double getHeight() {
		switch (direction) {
		case SIDEWAYS:
			return getLengthAcrossFeed().asMillimeter();
		case UPRIGHT:
		default:
			return getLengthAlongFeed().asMillimeter();
		}
	}

	/**
	 * Gets the shape of the print page.
	 * @return returns the shape
	 */
	public Shape getShape() {
		if (getWidth()<getHeight()) {
			return Shape.PORTRAIT;
		} else if (getWidth()>getHeight()) {
			return Shape.LANDSCAPE;
		} else {
			return Shape.SQUARE;
		}
	}
}
