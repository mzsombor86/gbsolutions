package hu.mzsombor.jobmonitor.service;

import org.springframework.data.jpa.domain.Specification;

import hu.mzsombor.jobmonitor.model.Position;
import hu.mzsombor.jobmonitor.model.Position_;

public class PositionSpecifications {

	public static Specification<Position> hasKeyword(String keyword) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Position_.title)), ("%" + keyword + "%").toLowerCase());
	}
	
	public static Specification<Position> hasLocation(String location) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Position_.location)), ("%" + location + "%").toLowerCase());
	}
	
}
