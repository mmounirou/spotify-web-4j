package com.mmounirou.spoty4j.api;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * add support for covert art. The covert art are retreived using the open.spotify.com web site. This solution is
 * awkward but it's the best i found
 * @author mmounirou
 */
public final class Open
{
	private static final String BASE_URL = "http://open.spotify.com";
	private static final String COVER_CSS_ID = "cover-art";
	private static final String CORVER_CSS_ATTR = "src";

	private Open()
	{
	}

	public static String getArtistCovertArt(String strHref) throws IOException
	{
		return getCovertArt(strHref, "artist");
	}

	public static String getAlbumCovertArt(String strHref) throws IOException
	{
		return getCovertArt(strHref, "album");
	}

	public static String getTrackCovertArt(String strHref) throws IOException
	{
		return getCovertArt(strHref, "track");
	}

	private static String getCovertArt(String strHref, String kind) throws IOException
	{
		String strAlbum = StringUtils.removeStart(strHref, String.format("spotify:%s:", kind));
		Document document = Jsoup.connect(String.format("%s/%s/%s", BASE_URL, kind, strAlbum)).get();
		Element imgElement = document.getElementById(COVER_CSS_ID);
		return imgElement.attr(CORVER_CSS_ATTR);
	}

}
