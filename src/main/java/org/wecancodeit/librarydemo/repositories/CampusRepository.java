package org.wecancodeit.librarydemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.librarydemo.models.Campus;

public interface CampusRepository extends CrudRepository<Campus, Long> {

    Campus findCampusByLocation(String location);
}
