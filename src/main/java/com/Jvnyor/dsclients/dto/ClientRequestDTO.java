package com.Jvnyor.dsclients.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;

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

	public ClientRequestDTO() {
	}

	public ClientRequestDTO(String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientRequestDTO other = (ClientRequestDTO) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
}
