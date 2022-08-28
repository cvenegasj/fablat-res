package net.fablat.fablatres.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "FabberInfo")
@Getter
@Setter
public class FabberInfo implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFabberInfo", unique = true, nullable = false)
	private Integer idFabberInfo;

	@Column(name = "scoreGeneral", nullable = false)
	private Integer scoreGeneral;

	@Column(name = "scoreCoordinator", nullable = false)
	private Integer scoreCoordinator;

	@Column(name = "scoreCollaborator", nullable = false)
	private Integer scoreCollaborator;

	@Column(name = "scoreReplicator", nullable = false)
	private Integer scoreReplicator;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFabber")
	private Fabber fabber;
}
