package com.cyclingrecord.data;

import com.cyclingrecord.models.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    void deleteById(String image);
}
