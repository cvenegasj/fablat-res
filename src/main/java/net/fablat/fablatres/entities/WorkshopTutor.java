package net.fablat.fablatres.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "WorkshopTutor")
public class WorkshopTutor implements java.io.Serializable {

	private Integer idWorkshopTutor;
	private SubGroupMember subGroupMember;
	private Workshop workshop;

	public WorkshopTutor() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idWorkshopTutor", unique = true, nullable = false)
	public Integer getIdWorkshopTutor() {
		return idWorkshopTutor;
	}

	public void setIdWorkshopTutor(Integer idWorkshopTutor) {
		this.idWorkshopTutor = idWorkshopTutor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSubGroupMember", nullable = false)
	public SubGroupMember getSubGroupMember() {
		return subGroupMember;
	}

	public void setSubGroupMember(SubGroupMember subGroupMember) {
		this.subGroupMember = subGroupMember;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idWorkshop", nullable = false)
	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

}
