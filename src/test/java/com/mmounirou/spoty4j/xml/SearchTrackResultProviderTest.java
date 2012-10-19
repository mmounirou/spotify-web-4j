package com.mmounirou.spoty4j.xml;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.SearchTrackResultProvider.TrackSearchResult;

public class SearchTrackResultProviderTest
{

	private SearchTrackResultProvider m_trackSearchResultProvider;

	@Before
	public void createTrackSearchResultProvider() throws Exception
	{
		m_trackSearchResultProvider = new SearchTrackResultProvider();
	}

	@Test
	public void testIsReadable()
	{
		assertThat(m_trackSearchResultProvider.isReadable(TrackSearchResult.class, null, null, null)).isTrue();
	}

	@Test
	public void testIsNotReadable()
	{
		assertThat(m_trackSearchResultProvider.isReadable(Object.class, null, null, null)).isFalse();
	}

	@Test
	public void testReadFrom() throws WebApplicationException, IOException
	{
		InputStream entityStream = SearchTrackResultProviderTest.class.getResourceAsStream("/search-track.xml");
		TrackSearchResult result = m_trackSearchResultProvider.readFrom(TrackSearchResult.class, null, null, null, null, entityStream);

		ImmutableList<Track> tracks = result.getTracks();
		assertThat(tracks).hasSize(2);
		Track track = tracks.get(0);

		assertThat(track.getHref()).isEqualTo("spotify:track:3ZsjgLDSvusBgxGWrTAVto");
		assertThat(track.getName()).isEqualTo("The Pretender");
		assertThat(track.getArtist().getHref()).isEqualTo("spotify:artist:7jy3rLJdDQY21OgRLCZ9sD");
		assertThat(track.getArtist().getName()).isEqualTo("Foo Fighters");
		assertThat(track.getId()).isEqualTo("USRW30700007");
		assertThat(track.getPopularity()).isEqualTo(0.79251);
		assertThat(track.getLength()).isEqualTo(269.373000);
		assertThat(track.getTrackNumber()).isEqualTo(1);
		assertThat(track.getAlbum().getHref()).isEqualTo("spotify:album:2ovXUTjkBkNlgAU7DTIxX9");
		assertThat(track.getAlbum().getName()).isEqualTo("Echoes, Silence, Patience - Grace");
		assertThat(track.getAlbum().getReleased()).isEqualTo(2007);
		
		
	}

}
