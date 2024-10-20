package com.wikimedia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WikimediaData {

	@Id
	@Column(name = "wm_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//The @Lob annotation is used because a huge amount of data will be stored in the wikimediaEventData attribute
	@Lob
	@Column(name = "wm_data", columnDefinition = "LONGTEXT")
	private String wikimediaEventData;
}
