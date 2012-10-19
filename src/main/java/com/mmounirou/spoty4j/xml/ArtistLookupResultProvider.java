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

public class ArtistLookupResultProvider implements MessageBodyReader<Artist>
{

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return Artist.class.isAssignableFrom(type);
	}

	public Artist readFrom(Class<Artist> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
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
		digester.addObjectCreate("artist", Artist.class);
		digester.addBeanPropertySetter("artist/name");
		
		digester.addObjectCreate("artist/albums/album", Album.class);
		digester.addSetNext("artist/albums/album", "addAlbum");
		
		digester.addSetProperties("artist/albums/album", "href", "href");
		digester.addBeanPropertySetter("artist/albums/album/name");
		digester.addBeanPropertySetter("artist/albums/album/id");
		digester.addBeanPropertySetter("artist/albums/album/released");


	}

}
