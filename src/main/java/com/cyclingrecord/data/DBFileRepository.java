package com.cyclingrecord.data;

import com.cyclingrecord.models.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, String> {
}
