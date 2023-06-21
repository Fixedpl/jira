package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nlo.jira.entity.ImageModel;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface ImageDataRepository extends JpaRepository<ImageModel, Integer> {

    Optional<ImageModel> findByName(String imageName);

}
