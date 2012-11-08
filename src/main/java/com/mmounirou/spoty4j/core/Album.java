package com.mmounirou.spoty4j.core;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mmounirou.spoty4j.api.Lookup;

public class Album
{
	private String m_name;
	private String m_href;
	private Artist m_artist;
	private String m_id;
	private int m_released;
	private double m_popularity;

	private String m_territories;
	private List<Track> m_tracks = Lists.newArrayList();

	public Album(String uri, String name)
	{
		m_href = uri;
		m_name = name;
	}

	public Album()
	{
	}

	public Album fetch()
	{
		Album fetchAlbum = Lookup.fetchAlbum(this);
		fetchAlbum.setHref(this.getHref());
		return fetchAlbum;
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
		return m_href;
	}

	public void setHref(String uri)
	{
		m_href = uri;
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

	public int getReleased()
	{
		return m_released;
	}

	public void setReleased(int release)
	{
		m_released = release;
	}

	public List<Track> getTracks()
	{
		return ImmutableList.copyOf(m_tracks);
	}

	public void setTracks(List<Track> tracks)
	{
		m_tracks.addAll(tracks);
	}

	public void addTrack(Track track)
	{
		m_tracks.add(track);
	}

	public String getTerritories()
	{
		return m_territories;
	}

	public void setTerritories(String territories)
	{
		m_territories = territories;
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
		return Objects.hashCode(m_href);
	}

	@Override
	public boolean equals(Object obj)
	{
		if ( this == obj )
		{
			return true;
		}

		if ( !(obj instanceof Album) )
		{
			return false;
		}
		return Objects.equal(((Album) obj).m_href, m_href);
	}

	@Override
	public String toString()
	{
		return m_name;
	}

}
