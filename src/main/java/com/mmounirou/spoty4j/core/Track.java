package com.mmounirou.spoty4j.core;

import com.mmounirou.spoty4j.api.Lookup;

public class Track
{
	private String m_name;
	private String m_href;
	private Artist m_artist;
	private Album album;
	private String id;
	private int diskNumber;
	private int trackNumber;
	private double length;
	private double popularity;
	private boolean available;

	public Track(String uri, String name)
	{
		m_href = uri;
		m_name = name;
	}

	public Track()
	{
	}

	public Track fetch()
	{
		Track fetchTrack = Lookup.fetchTrack(this);
		fetchTrack.setHref(this.getHref());
		return fetchTrack;
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

	public Album getAlbum()
	{
		return album;
	}

	public void setAlbum(Album album)
	{
		this.album = album;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getDiskNumber()
	{
		return diskNumber;
	}

	public void setDiskNumber(int diskNumber)
	{
		this.diskNumber = diskNumber;
	}

	public int getTrackNumber()
	{
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber)
	{
		this.trackNumber = trackNumber;
	}

	public double getLength()
	{
		return length;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public double getPopularity()
	{
		return popularity;
	}

	public void setPopularity(double popularity)
	{
		this.popularity = popularity;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}

}
