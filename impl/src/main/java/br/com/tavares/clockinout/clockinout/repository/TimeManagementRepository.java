package br.com.tavares.clockinout.clockinout.repository;

import br.com.tavares.clockinout.clockinout.repository.entity.TimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeManagementRepository extends MongoRepository<TimeEntity, String> {

    List<TimeEntity> findAllByUserIdAndAndDate(String userid, String date);

}
