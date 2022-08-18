package com.Jvnyor.dsclients.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ClientRequestDTO {

	@NotNull
	@NotEmpty
	@Schema(example = "Nome Sobrenome")
	private String name;
	@NotNull
	@NotEmpty
	@Schema(example = "12345678910", minLength = 11, maxLength = 11)
	@Length(max = 11, min = 11)
	private String cpf;
	@NotNull
	@Schema(example = "3000.00")
	private Double income;
	@NotNull
	private LocalDate birthDate;
	@NotNull
	@Schema(example = "1")
	private Integer children;

}
