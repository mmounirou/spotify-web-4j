package com.mmounirou.spoty4j.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.TrackSearchResultProvider.TrackSearchResult;

public class TrackSearchResultProvider implements MessageBodyReader<TrackSearchResult>
{

	public static class TrackSearchResult
	{

		private List<Track> m_tracks = Lists.newArrayList();

		public ImmutableList<Track> getTracks()
		{
			return ImmutableList.copyOf(m_tracks);
		}

		public void addTrack(Track track)
		{
			m_tracks.add(track);
		}

	}

	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3)
	{
		return TrackSearchResult.class.isAssignableFrom(type);
	}

	public TrackSearchResult readFrom(Class<TrackSearchResult> arg0, Type arg1, Annotation[] arg2, MediaType arg3, MultivaluedMap<String, String> arg4, InputStream entityStream)
			throws IOException, WebApplicationException
	{
		Digester digester = new Digester();
		addRules(digester);

		try
		{
			return digester.parse(cleanStream(entityStream));
		} catch (SAXException e)
		{
			throw new IOException(e);
		}
	}

	private InputStream cleanStream(InputStream entityStream) throws IOException, UnsupportedEncodingException
	{
		String strStream = CharStreams.toString(new InputStreamReader(entityStream, Charsets.UTF_8.name()));
		String clean = strStream.replace("&", "-");
		return new ByteArrayInputStream(clean.getBytes());
	}
	
	private void addRules(Digester digester)
	{
		digester.addObjectCreate("tracks", TrackSearchResult.class);

		digester.addObjectCreate("tracks/track", Track.class);
		digester.addSetNext("tracks/track", "addTrack");

		digester.addBeanPropertySetter("tracks/track/name");
		digester.addBeanPropertySetter("tracks/track/id");
		digester.addBeanPropertySetter("tracks/track/length");
		digester.addBeanPropertySetter("tracks/track/popularity");
		digester.addBeanPropertySetter("tracks/track/track-number","trackNumber");
		digester.addSetProperties("tracks/track", "href", "href");

		digester.addObjectCreate("tracks/track/artist", Artist.class);
		digester.addSetNext("tracks/track/artist", "setArtist");

		digester.addSetProperties("tracks/track/artist", "href", "href");
		digester.addBeanPropertySetter("tracks/track/artist/name");

		digester.addObjectCreate("tracks/track/album", Album.class);
		digester.addSetNext("tracks/track/album", "setAlbum");

		digester.addSetProperties("tracks/track/album", "href", "href");
		digester.addBeanPropertySetter("tracks/track/album/name");
		digester.addBeanPropertySetter("tracks/track/album/released");
		digester.addBeanPropertySetter("tracks/track/album/availability/territories");

	}

}
