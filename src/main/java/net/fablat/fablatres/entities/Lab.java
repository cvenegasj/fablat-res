package net.fablat.fablatres.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Lab")
@Data
public class Lab implements java.io.Serializable {

	@Id
	@Column(name = "idLab", unique = true, nullable = false)
	private Integer idLab;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "url")
	private String url;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idLocation", nullable = false)
	private Location location;
}
