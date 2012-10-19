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

public class TrackLookupResultProvider implements MessageBodyReader<Track>
{

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return Track.class.isAssignableFrom(type);
	}

	public Track readFrom(Class<Track> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException
	{

		Digester digester = new Digester();
		addRules(digester);

		try
		{
			return digester.parse(entityStream);
		}
		catch ( SAXException e )
		{
			throw new IOException(e);
		}
	}

	private void addRules(Digester digester)
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

}
