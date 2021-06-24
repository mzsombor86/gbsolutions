package hu.mzsombor.jobmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mzsombor.jobmonitor.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
