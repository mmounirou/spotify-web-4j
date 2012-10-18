package com.mmounirou.spoty4j.core;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.api.Lookup;

public class Artist
{
	private String m_name;
	private String m_uri;
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
		return Lookup.fetchArtist(this);
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
}
