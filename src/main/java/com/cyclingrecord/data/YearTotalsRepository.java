package com.cyclingrecord.data;

import com.cyclingrecord.models.YearTotals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearTotalsRepository extends CrudRepository<YearTotals, Integer> {
}
