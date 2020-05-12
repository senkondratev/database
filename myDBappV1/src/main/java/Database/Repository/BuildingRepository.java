package Database.Repository;

import Database.Domain.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building, Integer> {
    public Building findByBuildingId(int id);
}
