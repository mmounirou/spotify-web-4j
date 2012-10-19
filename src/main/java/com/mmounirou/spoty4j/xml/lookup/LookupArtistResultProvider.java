package com.mmounirou.spoty4j.xml.lookup;

import org.apache.commons.digester3.Digester;

import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;

public class LookupArtistResultProvider extends DigesterMessageBodyReader<Artist>
{
	@Override
	protected void addRules(Digester digester)
	{
		digester.addObjectCreate("artist", Artist.class);
		digester.addBeanPropertySetter("artist/name");

		digester.addObjectCreate("artist/albums/album", Album.class);
		digester.addSetNext("artist/albums/album", "addAlbum");

		digester.addSetProperties("artist/albums/album", "href", "href");
		digester.addBeanPropertySetter("artist/albums/album/name");
		digester.addBeanPropertySetter("artist/albums/album/id");
		digester.addBeanPropertySetter("artist/albums/album/released");
	}

	@Override
	protected Class<Artist> getGenericClass()
	{
		return Artist.class;
	}

}
