package com.mmounirou.spoty4j.xml.lookup;

import org.apache.commons.digester3.Digester;

import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;

public class LookupTrackResultProvider extends DigesterMessageBodyReader<Track>
{

	protected void addRules(Digester digester)
	{
		digester.addObjectCreate("track", Track.class);

		digester.addBeanPropertySetter("track/name");
		digester.addBeanPropertySetter("track/id");
		digester.addBeanPropertySetter("track/length");
		digester.addBeanPropertySetter("track/popularity");
		digester.addBeanPropertySetter("track/track-number", "trackNumber");
		digester.addBeanPropertySetter("track/available");

		digester.addObjectCreate("track/artist", Artist.class);
		digester.addSetNext("track/artist", "setArtist");
		digester.addSetProperties("track/artist", "href", "href");
		digester.addBeanPropertySetter("track/artist/name");

		digester.addObjectCreate("track/album", Album.class);
		digester.addSetNext("track/album", "setAlbum");
		digester.addSetProperties("track/album", "href", "href");
		digester.addBeanPropertySetter("track/album/name");
		digester.addBeanPropertySetter("track/album/availability/territories");

	}

	@Override
	protected Class<Track> getGenericClass()
	{
		// TODO Auto-generated method stub
		return Track.class;
	}

}
