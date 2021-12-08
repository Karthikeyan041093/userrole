package com.example.i2i.userdetails.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.example.i2i.userdetails.dto.UserDTO;

@Component
public interface UserService {

	public UserDTO saveUser(UserDTO user);

	public List<UserDTO> getAllUsersWithPagination(Integer pageNo, Integer pageSize);

	public UserDTO findById(int id);

	public List<UserDTO> getAllUsers();

	public UserDTO updateUser(UserDTO user);

	public void deleteUserById(int id);

	public UserDTO patchUpdateUser(int id, Map<Object, Object> userObj);

}
