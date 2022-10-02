package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//getters and setters from lombok
@Entity
@Getter
@Setter
@ToString
public class room {

	@Id
	private int roomId;
	private String roomName;
	private String roomType;
}