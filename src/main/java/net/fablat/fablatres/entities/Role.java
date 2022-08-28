package net.fablat.fablatres.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
@Data
public class Role implements java.io.Serializable {

	@Id
	@Column(name = "idRole", unique = true, nullable = false)
	private Integer idRole;

	@Column(name = "name", unique = true, nullable = false)
	private String name;
}
