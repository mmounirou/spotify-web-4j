package com.mmounirou.spoty4j.xml;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mmounirou.spoty4j.core.Track;

public class TrackLookupResultProviderTest
{

	private TrackLookupResultProvider m_trackLookupResultProvider;

	@Before
	public void createTrackLookupResultProvider() throws Exception
	{
		m_trackLookupResultProvider = new TrackLookupResultProvider();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testIsReadable()
	{
		assertThat(m_trackLookupResultProvider.isReadable(Track.class, null, null, null)).isTrue();
	}

	@Test
	public void testNotReadable()
	{
		assertThat(m_trackLookupResultProvider.isReadable(Object.class, null, null, null)).isFalse();
	}

	@Test
	public void testReadFrom() throws WebApplicationException, IOException
	{
		InputStream entityStream = Track.class.getResourceAsStream("/lookup-track.xml");
		Track track = m_trackLookupResultProvider.readFrom(Track.class, null, null, null, null, entityStream);

		assertThat(track.getName()).isEqualTo("Bon Fra Helvete - Live");

		assertThat(track.getId()).isEqualTo("NOPVA0203020");
		assertThat(track.getPopularity()).isEqualTo(0.40003);
		assertThat(track.getLength()).isEqualTo(317.040000);
		assertThat(track.getTrackNumber()).isEqualTo(2);

		assertThat(track.getArtist().getHref()).isEqualTo("spotify:artist:1s1DnVoBDfp3jxjjew8cBR");
		assertThat(track.getArtist().getName()).isEqualTo("Kaizers Orchestra");

		assertThat(track.getAlbum().getHref()).isEqualTo("spotify:album:6K8NUknbPh5TGaKeZdDwSg");
		assertThat(track.getAlbum().getName()).isEqualTo("Mann Mot Mann");

	}

}
