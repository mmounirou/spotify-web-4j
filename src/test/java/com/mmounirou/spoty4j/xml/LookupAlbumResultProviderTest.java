package com.mmounirou.spoty4j.xml;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import com.mmounirou.spoty4j.core.Album;

public class LookupAlbumResultProviderTest
{
	private LookupAlbumResultProvider m_albumLookupResultProvider;

	@Before
	public void createAlbumLookupResultProvider() throws Exception
	{
		m_albumLookupResultProvider = new LookupAlbumResultProvider();
	}

	@Test
	public void testIsReadable()
	{
		assertThat(m_albumLookupResultProvider.isReadable(Album.class, null, null, null)).isTrue();
	}

	@Test
	public void testNotReadable()
	{
		assertThat(m_albumLookupResultProvider.isReadable(Object.class, null, null, null)).isFalse();
	}

	@Test
	public void testReadFrom() throws WebApplicationException, IOException
	{
		InputStream entityStream = SearchAlbumResultProvider.class.getResourceAsStream("/lookup-album.xml");
		Album album = m_albumLookupResultProvider.readFrom(Album.class, null, null, null, null, entityStream);

		assertThat(album.getId()).isEqualTo("634904012922");
		assertThat(album.getName()).isEqualTo("Remedy");
		assertThat(album.getReleased()).isEqualTo(1999);
		assertThat(album.getArtist().getHref()).isEqualTo("spotify:artist:4YrKBkKSVeqDamzBPWVnSJ");
		assertThat(album.getArtist().getName()).isEqualTo("Basement Jaxx");
		assertThat(album.getTracks()).hasSize(1);
		assertThat(album.getTracks().get(0).getHref()).isEqualTo("spotify:track:3zBhJBEbDD4a4SO1EaEiBP");
		assertThat(album.getTracks().get(0).getName()).isEqualTo("Rendez-vu");
	}

}
