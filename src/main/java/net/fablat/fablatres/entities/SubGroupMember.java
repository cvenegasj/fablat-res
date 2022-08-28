package net.fablat.fablatres.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SubGroupMember")
public class SubGroupMember implements java.io.Serializable {

	private Integer idSubGroupMember;
	private Boolean isCoordinator;
	private Boolean notificationsEnabled;
	private LocalDateTime creationDateTime;
	private SubGroup subGroup;
	private GroupMember groupMember;
	private Set<WorkshopTutor> workshopTutors = new HashSet<WorkshopTutor>();

	public SubGroupMember() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSubGroupMember", unique = true, nullable = false)
	public Integer getIdSubGroupMember() {
		return idSubGroupMember;
	}

	public void setIdSubGroupMember(Integer idSubGroupMember) {
		this.idSubGroupMember = idSubGroupMember;
	}

	@Column(name = "isCoordinator", nullable = false)
	public Boolean getIsCoordinator() {
		return isCoordinator;
	}

	public void setIsCoordinator(Boolean isCoordinator) {
		this.isCoordinator = isCoordinator;
	}

	@Column(name = "notificationsEnabled", nullable = false)
	public Boolean getNotificationsEnabled() {
		return notificationsEnabled;
	}

	public void setNotificationsEnabled(Boolean notificationsEnabled) {
		this.notificationsEnabled = notificationsEnabled;
	}

	@Column(name = "creationDateTime", nullable = false)
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSubGroup", nullable = false)
	public SubGroup getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(SubGroup subGroup) {
		this.subGroup = subGroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGroupMember", nullable = false)
	public GroupMember getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(GroupMember groupMember) {
		this.groupMember = groupMember;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subGroupMember")
	public Set<WorkshopTutor> getWorkshopTutors() {
		return workshopTutors;
	}

	public void setWorkshopTutors(Set<WorkshopTutor> workshopTutors) {
		this.workshopTutors = workshopTutors;
	}
}
