package org.gwtbook.client.ui.impl;

import org.gwtbook.client.ui.FlashMovie.FlashMovieParameters;

public class FlashMovieImplIE6 extends FlashMovieImpl {

	public String createMovie(FlashMovieParameters params) {
		String movie = "<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#\\ version=6,0,40,0\" name=\"demoMovie\" id=\"demoMovie\" width=\"318\" height=\"252\">";
		movie += "<param name=\"movie\" value=flash_movie.swf?slogan=&slogan_white=&title=IE6&title_white=IE6&url= />";
		movie += "<param name=\"play\" value=\"true\" />";
		movie += "<param name=\"loop\" value=\"false\" />";
		movie += "<param name=\"quality\" value=\"high\" />";
		movie += "</object>";
		return movie;
	}

}
