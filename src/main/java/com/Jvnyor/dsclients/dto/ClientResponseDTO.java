package com.Jvnyor.dsclients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ClientResponseDTO {

	private Long id;
	private String name;
	private String cpf;
	private Double income;
	private String birthDate;
	private Integer children;

}
