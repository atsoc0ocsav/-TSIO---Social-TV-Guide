package iul.iscte.tsio.model;

import java.util.Calendar;

public class ProgramEntity {
	
	private String title;
	private String type;
	private Calendar startTime;
	private Calendar endTime;
	private String channel;
	private String description;
	private int season = 0;
	private int episodeNumber = 0;
	
	public ProgramEntity(String title, String type, Calendar startTime,
			Calendar endTime, String channel, String description) {
		super();
		this.title = title;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.channel = channel;
		this.description = description;
	}
	
	public ProgramEntity(String title, String type, Calendar startTime,
			Calendar endTime, String channel, String description, int season, int episodeNumber) {
		super();
		this.title = title;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.channel = channel;
		this.description = description;
		this.season = season;
		this.episodeNumber = episodeNumber;
	}


	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public String getChannel() {
		return channel;
	}

	public String getDescription() {
		return description;
	}
}
