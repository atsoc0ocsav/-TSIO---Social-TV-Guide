package iul.iscte.tsio.model;

public class ProgramEntity {

	private String title;
	private String type;
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
		this.runtime = runtime;
		this.description = description;
	}
	
	public ProgramEntity(long nodeId, String title, String type, int runtime,
			String description) {
		super();
		this.title = title;
		this.type = type;
		this.runtime = runtime;
		this.description = description;
		this.nodeId = nodeId;
	}
	
	public ProgramEntity(String title, String type, int runtime,
			String description, int season, int episodeNumber) {
		super();
		this.title = title;
		this.type = type;
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

	public int getRuntime() {
		return runtime;
	}

	public String getDescription() {
		return description;
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
	
	@Override
	public String toString() {
		return "ProgramEntity [title=" + title + ", type=" + type
				+ ", runtime=" + runtime + ", description=" + description
				+ ", season=" + season + ", episodeNumber=" + episodeNumber
				+ ", nodeId=" + nodeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + episodeNumber;
		result = prime * result + (int) (nodeId ^ (nodeId >>> 32));
		result = prime * result + runtime;
		result = prime * result + season;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramEntity other = (ProgramEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (episodeNumber != other.episodeNumber)
			return false;
		if (nodeId != other.nodeId)
			return false;
		if (runtime != other.runtime)
			return false;
		if (season != other.season)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
