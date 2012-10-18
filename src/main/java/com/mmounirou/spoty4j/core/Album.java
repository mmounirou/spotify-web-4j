package com.mmounirou.spoty4j.core;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.api.Lookup;

public class Album
{
	private String m_name;
	private String m_uri;
	private Artist m_artist;
	private String m_id;
	private int m_release;

	private List<String> m_territories = Lists.newArrayList();
	private List<Track> m_tracks = Lists.newArrayList();

	public Album(String uri, String name)
	{
		m_uri = uri;
		m_name = name;
	}

	public Album()
	{
	}

	public Album fetch()
	{
		return Lookup.fetchAlbum(this);
	}

	public String getName()
	{
		return m_name;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public String getUri()
	{
		return m_uri;
	}

	public void setUri(String uri)
	{
		m_uri = uri;
	}

	public Artist getArtist()
	{
		return m_artist;
	}

	public void setArtist(Artist artist)
	{
		m_artist = artist;
	}

	public String getId()
	{
		return m_id;
	}

	public void setId(String id)
	{
		m_id = id;
	}

	public int getRelease()
	{
		return m_release;
	}

	public void setRelease(int release)
	{
		m_release = release;
	}

	public List<String> getTerritories()
	{
		return ImmutableList.copyOf(m_territories);
	}

	public void setTerritories(List<String> territories)
	{
		m_territories.addAll(territories);
	}

	public List<Track> getTracks()
	{
		return ImmutableList.copyOf(m_tracks);
	}

	public void setTracks(List<Track> tracks)
	{
		m_tracks.addAll(tracks);
	}

}
