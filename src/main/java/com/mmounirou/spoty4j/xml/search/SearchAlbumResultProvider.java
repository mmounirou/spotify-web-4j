package com.mmounirou.spoty4j.xml.search;

import java.util.List;

import org.apache.commons.digester3.Digester;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;
import com.mmounirou.spoty4j.xml.search.SearchAlbumResultProvider.AlbumSearchResult;

public class SearchAlbumResultProvider extends DigesterMessageBodyReader<AlbumSearchResult>
{
	public static final class AlbumSearchResult
	{

		private List<Album> m_albums = Lists.newArrayList();

		public ImmutableList<Album> getAlbums()
		{
			return ImmutableList.copyOf(m_albums);
		}

		public void addAlbum(Album album)
		{
			m_albums.add(album);
		}

	}

	protected void addRules(Digester digester)
	{
		digester.addObjectCreate("albums", AlbumSearchResult.class);

		digester.addObjectCreate("albums/album", Album.class);
		digester.addSetNext("albums/album", "addAlbum");

		digester.addBeanPropertySetter("albums/album/name");
		digester.addBeanPropertySetter("albums/album/id");
		digester.addBeanPropertySetter("albums/album/popularity");
		digester.addBeanPropertySetter("albums/album/name");
		digester.addBeanPropertySetter("albums/album/availability/territories");
		digester.addSetProperties("albums/album", "href", "href");

		digester.addObjectCreate("albums/album/artist", Artist.class);
		digester.addSetNext("albums/album/artist", "setArtist");

		digester.addSetProperties("albums/album/artist", "href", "href");
		digester.addBeanPropertySetter("albums/album/artist/name");

	}

	@Override
	protected Class<AlbumSearchResult> getGenericClass()
	{
		return AlbumSearchResult.class;
	}

}
