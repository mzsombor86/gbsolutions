package hu.mzsombor.jobmonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mzsombor.jobmonitor.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> findByEmail(String email);

	List<Client> findByUuid(String uuid);
}
