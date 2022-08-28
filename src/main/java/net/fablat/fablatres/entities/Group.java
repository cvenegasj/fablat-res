package net.fablat.fablatres.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Group")
@Getter
@Setter
public class Group implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idGroup", unique = true, nullable = false)
	private Integer idGroup;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "reunionDay")
	private String reunionDay;

	@Column(name = "reunionTime")
	private LocalTime reunionTime;

	@Column(name = "mainUrl")
	private String mainUrl;

	@Column(name = "secondaryUrl")
	private String secondaryUrl;

	@Column(name = "photoUrl")
	private String photoUrl;

	@Column(name = "creationDateTime", nullable = false)
	private LocalDateTime creationDateTime;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	@OrderBy("date(creationDateTime) asc")
	private Set<GroupMember> groupMembers = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<SubGroup> subGroups = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<ActivityLog> activities = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGroup == null) ? 0 : idGroup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (idGroup == null) {
			if (other.idGroup != null)
				return false;
		} else if (!idGroup.equals(other.idGroup))
			return false;
		return true;
	}
}
