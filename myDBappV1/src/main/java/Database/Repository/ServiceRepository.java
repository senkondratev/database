package Database.Repository;

import Database.Domain.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
    public Service findByServiceId(int i);
    public List<Service> findByBuilding_BuildingId(int i);
}
