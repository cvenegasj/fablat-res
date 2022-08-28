package net.fablat.fablatres.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "GroupMember")
@Data
public class GroupMember implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idGroupMember", unique = true, nullable = false)
	private Integer idGroupMember;

	@Column(name = "isCoordinator", nullable = false)
	private Boolean isCoordinator;

	@Column(name = "notificationsEnabled", nullable = false)
	private Boolean notificationsEnabled;

	@Column(name = "creationDateTime", nullable = false)
	private LocalDateTime creationDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFabber", nullable = false)
	private Fabber fabber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGroup", nullable = false)
	private Group group;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupMember")
	@OrderBy("date(creationDateTime) asc")
	private Set<SubGroupMember> subGroupMembers = new HashSet<>();
}
