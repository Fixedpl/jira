package pl.nlo.jira.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;

/**
 * @author marcin
 * @since 09.06.2023
 */
@Component
public class UserConverter implements Converter<UserEntity, UserDTO> {

	@Override
	public UserDTO convert(UserEntity source) {
		return UserDTO.builder()
				.id(source.getId())
				.email(source.getEmail())
				.firstName(source.getFirstName())
				.lastName(source.getLastName())
				.build();
	}
}
