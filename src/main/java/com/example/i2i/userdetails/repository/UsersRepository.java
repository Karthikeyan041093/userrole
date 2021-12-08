package com.example.i2i.userdetails.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.i2i.userdetails.entity.User;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer> {

	@Transactional
	void deleteByUserId(int id);

}
