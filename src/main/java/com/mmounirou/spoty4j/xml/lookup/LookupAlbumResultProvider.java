package com.mmounirou.spoty4j.xml.lookup;

import org.apache.commons.digester3.Digester;

import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;

public class LookupAlbumResultProvider extends DigesterMessageBodyReader<Album>
{
	protected void addRules(Digester digester)
	{
		// TODO merge rules between lookup and search

		digester.addObjectCreate("album", Album.class);

		digester.addBeanPropertySetter("album/name");
		digester.addBeanPropertySetter("album/id");
		digester.addBeanPropertySetter("album/availability/territories");
		digester.addBeanPropertySetter("album/released");

		digester.addObjectCreate("album/artist", Artist.class);
		digester.addSetNext("album/artist", "setArtist");

		digester.addSetProperties("album/artist", "href", "href");
		digester.addBeanPropertySetter("album/artist/name");

		digester.addObjectCreate("album/tracks/track", Track.class);
		digester.addSetNext("album/tracks/track", "addTrack");

		digester.addBeanPropertySetter("album/tracks/track/name");
		digester.addBeanPropertySetter("album/tracks/track/id");
		digester.addBeanPropertySetter("album/tracks/track/length");
		digester.addBeanPropertySetter("album/tracks/track/popularity");
		digester.addBeanPropertySetter("album/tracks/track/track-number", "trackNumber");
		digester.addSetProperties("album/tracks/track", "href", "href");

		digester.addObjectCreate("album/tracks/track/artist", Artist.class);
		digester.addSetNext("album/tracks/track/artist", "setArtist");

		digester.addSetProperties("album/tracks/track/artist", "href", "href");
		digester.addBeanPropertySetter("album/tracks/track/artist/name");

		digester.addObjectCreate("album/tracks/track/album", Album.class);
		digester.addSetNext("album/tracks/track/album", "setAlbum");

		digester.addSetProperties("album/tracks/track/album", "href", "href");
		digester.addBeanPropertySetter("album/tracks/track/album/name");
		digester.addBeanPropertySetter("album/tracks/track/album/released");
		digester.addBeanPropertySetter("album/tracks/track/album/availability/territories");

	}

	@Override
	protected Class<Album> getGenericClass()
	{
		return Album.class;
	}

}
