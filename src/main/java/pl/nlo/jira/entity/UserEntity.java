package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.nlo.jira.entity.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author marcin
 * @since 15.03.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_base")
@Table(name = "user_base")
public class UserEntity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
	private Integer id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String location;
	private String organizationName;
	private String password;
	private RoleEnum roleEnum;
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] avatar;
	private String phoneNumber;
	private LocalDate birthday;

	@OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Task> tasksReported = new ArrayList<>();
	@OneToMany(mappedBy = "assigned", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Task> tasksAssigned = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(roleEnum.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
