package com.infy.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "sequence")
public class SequenceId {
	@Id
	private String id;
	private Long seq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "SequenceId [id=" + id + ", seq=" + seq + "]";
	}
}
