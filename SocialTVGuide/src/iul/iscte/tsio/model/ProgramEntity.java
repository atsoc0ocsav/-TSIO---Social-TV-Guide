package iul.iscte.tsio.model;

import java.util.Calendar;

public class ProgramEntity {

	private String title;
	private String type;
	// private Calendar startTime;
	// private Calendar endTime;
	// private String channel;
	private int runtime;
	private String description;
	private int season = 0;
	private int episodeNumber = 0;
	private long nodeId;
	
	public ProgramEntity(String title, String type, int runtime,
			String description) {
		super();
		this.title = title;
		this.type = type;
		// this.startTime = startTime;
		// this.endTime = endTime;
		// this.channel = channel;
		this.runtime = runtime;
		this.description = description;
	}
	
	public ProgramEntity(long nodeId, String title, String type, int runtime,
			String description) {
		super();
		this.title = title;
		this.type = type;
		// this.startTime = startTime;
		// this.endTime = endTime;
		// this.channel = channel;
		this.runtime = runtime;
		this.description = description;
		this.nodeId = nodeId;
	}
	
	public ProgramEntity(String title, String type, int runtime,
			String description, int season, int episodeNumber) {
		super();
		this.title = title;
		this.type = type;
		// this.startTime = startTime;
		// this.endTime = endTime;
		// this.channel = channel;
		this.runtime = runtime;
		this.description = description;
		this.season = season;
		this.episodeNumber = episodeNumber;
	}
	
	public ProgramEntity(long nodeId, String title, String type, int runtime,
			String description, int season, int episodeNumber) {
		super();
		this.title = title;
		this.type = type;
		// this.startTime = startTime;
		// this.endTime = endTime;
		// this.channel = channel;
		this.runtime = runtime;
		this.description = description;
		this.season = season;
		this.episodeNumber = episodeNumber;
		this.nodeId = nodeId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	// public Calendar getStartTime() {
	// return startTime;
	// }
	//
	// public Calendar getEndTime() {
	// return endTime;
	// }
	//
	// public String getChannel() {
	// return channel;
	// }

	public int getRuntime() {
		return runtime;
	}

	public String getDescription() {
		return description;
	}
	
	public long getId(){
		return nodeId;
	}
	
	public void setId(long nodeId){
		this.nodeId = nodeId;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
