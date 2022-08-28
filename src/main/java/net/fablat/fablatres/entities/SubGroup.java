package net.fablat.fablatres.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "SubGroup")
@Getter
@Setter
public class SubGroup implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSubGroup", unique = true, nullable = false)
	private Integer idSubGroup;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGroup", nullable = false)
	private Group group;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subGroup")
	//@Fetch(FetchMode.JOIN)
	private Set<SubGroupMember> subGroupMembers = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subGroup")
	@Fetch(FetchMode.JOIN)
	@OrderBy("date(startDateTime) asc")
	private Set<Workshop> workshops = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subGroup")
	@Fetch(FetchMode.JOIN)
	private Set<ActivityLog> activities = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSubGroup == null) ? 0 : idSubGroup.hashCode());
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
		SubGroup other = (SubGroup) obj;
		if (idSubGroup == null) {
			if (other.idSubGroup != null)
				return false;
		} else if (!idSubGroup.equals(other.idSubGroup))
			return false;
		return true;
	}
}
