package com.mmounirou.spoty4j.xml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;

public class AlbumLookupResultProvider implements MessageBodyReader<Album>
{

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return Album.class.isAssignableFrom(type);
	}

	public Album readFrom(Class<Album> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException
	{
		Digester digester = new Digester();
		addRules(digester);

		try
		{
			return digester.parse(entityStream);
		} catch (SAXException e)
		{
			throw new IOException(e);
		}
	}

	private void addRules(Digester digester)
	{
		//TODO merge rules between lookup and search
		
		digester.addObjectCreate("album", Album.class);
		digester.addSetNext("album", "addAlbum");

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

}
