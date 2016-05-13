package org.gwtbook.client.ui.impl;

import org.gwtbook.client.ui.FlashMovie.FlashMovieParameters;

public class FlashMovieImpl {

	public String createMovie(FlashMovieParameters params) {
		String theMovie = "";
		theMovie += "<embed src=\"flash_movie.swf?slogan=&slogan_white=&title=Other&title_white=Other&url=\" width=\"318\" height=\"252\" play=\"true\" loop=\"false\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\"> <noembed>  Error: No Object or Embed Support</noembed></embed>";
		return theMovie;
	}
}
