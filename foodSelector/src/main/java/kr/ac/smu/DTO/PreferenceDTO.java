package kr.ac.smu.DTO;

import lombok.Data;

@Data
public class PreferenceDTO {
	private String userId;
	private String id;
	private boolean flag;
	private boolean custom;
	private String customName;	
}
