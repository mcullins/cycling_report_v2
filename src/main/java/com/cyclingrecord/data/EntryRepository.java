package com.cyclingrecord.data;

import com.cyclingrecord.models.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Integer> {
    Entry findByDate(String date);
}
