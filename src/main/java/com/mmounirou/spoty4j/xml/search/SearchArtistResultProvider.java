package com.mmounirou.spoty4j.xml.search;

import java.util.List;

import org.apache.commons.digester3.Digester;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.xml.DigesterMessageBodyReader;
import com.mmounirou.spoty4j.xml.search.SearchArtistResultProvider.ArtistSearchResult;

public class SearchArtistResultProvider extends DigesterMessageBodyReader<ArtistSearchResult>
{

	public static class ArtistSearchResult
	{

		private List<Artist> m_artists = Lists.newArrayList();

		public ImmutableList<Artist> getArtists()
		{
			return ImmutableList.copyOf(m_artists);
		}

		public void addArtist(Artist artist)
		{
			m_artists.add(artist);
		}

	}

	protected void addRules(Digester digester)
	{
		digester.addObjectCreate("artists", ArtistSearchResult.class);

		digester.addObjectCreate("artists/artist", Artist.class);
		digester.addSetNext("artists/artist", "addArtist");

		digester.addBeanPropertySetter("artists/artist/name");
		digester.addBeanPropertySetter("artists/artist/id");
		digester.addBeanPropertySetter("artists/artist/popularity");
		digester.addSetProperties("artists/artist", "href", "href");
	}

	@Override
	protected Class<ArtistSearchResult> getGenericClass()
	{
		return ArtistSearchResult.class;
	}

}
