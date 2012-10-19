package com.mmounirou.spoty4j.xml;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import com.mmounirou.spoty4j.core.Artist;

public class LookupArtistResultProviderTest
{

	private LookupArtistResultProvider m_artistLookupResultProvider;

	@Before
	public void createArtistLookupResultProvider() throws Exception
	{
		m_artistLookupResultProvider = new LookupArtistResultProvider();
	}

	@Test
	public void testIsReadable()
	{
		assertThat(m_artistLookupResultProvider.isReadable(Artist.class, null, null, null)).isTrue();
	}

	@Test
	public void testIsFalse()
	{
		assertThat(m_artistLookupResultProvider.isReadable(Object.class, null, null, null)).isFalse();
	}

	@Test
	public void testReadFrom() throws WebApplicationException, IOException
	{
		InputStream entityStream = Artist.class.getResourceAsStream("/lookup-artist.xml");
		Artist artist = m_artistLookupResultProvider.readFrom(Artist.class, null, null, null, null, entityStream);

		assertThat(artist.getName()).isEqualTo("Basement Jaxx");
		assertThat(artist.getAlbums()).hasSize(1);
		assertThat(artist.getAlbums().get(0).getHref()).isEqualTo("spotify:album:0vQbb9UeAU7R8wk3ZAjNx6");
		assertThat(artist.getAlbums().get(0).getName()).isEqualTo("Crazy Itch Radio");
		assertThat(artist.getAlbums().get(0).getReleased()).isEqualTo(2006);
		assertThat(artist.getAlbums().get(0).getId()).isEqualTo("634904020583");

	}

}
