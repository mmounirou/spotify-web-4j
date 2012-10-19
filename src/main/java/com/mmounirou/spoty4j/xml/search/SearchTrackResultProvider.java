package com.mmounirou.spoty4j.xml.search;

import java.util.List;

import org.apache.commons.digester3.Digester;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;
import com.mmounirou.spoty4j.xml.search.SearchTrackResultProvider.TrackSearchResult;

public class SearchTrackResultProvider extends DigesterMessageBodyReader<TrackSearchResult>
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

	protected void addRules(Digester digester)
	{
		digester.addObjectCreate("tracks", TrackSearchResult.class);

		digester.addObjectCreate("tracks/track", Track.class);
		digester.addSetNext("tracks/track", "addTrack");

		digester.addBeanPropertySetter("tracks/track/name");
		digester.addBeanPropertySetter("tracks/track/id");
		digester.addBeanPropertySetter("tracks/track/length");
		digester.addBeanPropertySetter("tracks/track/popularity");
		digester.addBeanPropertySetter("tracks/track/track-number", "trackNumber");
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

	@Override
	protected Class<TrackSearchResult> getGenericClass()
	{
		return TrackSearchResult.class;
	}

}
