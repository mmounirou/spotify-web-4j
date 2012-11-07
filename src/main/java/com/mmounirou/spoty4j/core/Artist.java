package com.mmounirou.spoty4j.core;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.api.Lookup;

public class Artist
{
	private String m_name;
	private String m_uri;
	private String m_id;
	private double m_popularity;
	private List<Album> m_albums = Lists.newArrayList();

	public Artist(String strUri, String strName)
	{
		m_uri = strUri;
		m_name = strName;
	}

	public Artist()
	{
	}

	public Artist fetch()
	{
		Artist fetchArtist = Lookup.fetchArtist(this);
		fetchArtist.setHref(this.getHref());
		return fetchArtist;
	}

	public String getName()
	{
		return m_name;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public String getHref()
	{
		return m_uri;
	}

	public void setHref(String uri)
	{
		m_uri = uri;
	}

	public List<Album> getAlbums()
	{
		return ImmutableList.copyOf(m_albums);
	}

	public void setAlbums(List<Album> albums)
	{
		m_albums.addAll(albums);
	}

	public void addAlbum(Album album)
	{
		m_albums.add(album);
	}

	public String getId()
	{
		return m_id;
	}

	public void setId(String id)
	{
		m_id = id;
	}

	public double getPopularity()
	{
		return m_popularity;
	}

	public void setPopularity(double popularity)
	{
		m_popularity = popularity;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(m_uri);
	}

	@Override
	public boolean equals(Object obj)
	{
		if ( this == obj )
		{
			return true;
		}

		if ( !(obj instanceof Artist) )
		{
			return false;
		}
		return Objects.equal(((Artist) obj).m_uri, m_uri);
	}

}
