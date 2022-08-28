package net.fablat.fablatres.entities;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "RoleFabber")
@Data
public class RoleFabber implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idRoleFabber", unique = true, nullable = false)
	private Integer idRoleFabber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRole", nullable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFabber", nullable = false)
	private Fabber fabber;
}
