package net.fablat.fablatres.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ActivityLog")
@Data
public class ActivityLog implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idActivityLog", unique = true, nullable = false)
	private Integer idActivityLog;

	@Column(name = "level", nullable = false)
	private String level;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "visibility", nullable = false)
	private String visibility;

	@Column(name = "creationDateTime", nullable = false)
	private LocalDateTime creationDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFabber", nullable = false)
	private Fabber fabber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGroup")
	private Group group;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSubGroup")
	private SubGroup subGroup;
}
