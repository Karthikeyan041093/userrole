package com.example.i2i.userdetails.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import com.example.i2i.userdetails.dto.UserDTO;
import com.example.i2i.userdetails.entity.User;
import com.example.i2i.userdetails.repository.UsersRepository;
import com.example.i2i.userdetails.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UsersRepository usersRepo;
	private ModelMapper modelMapper;

	public UserServiceImpl(UsersRepository usersRepo, ModelMapper modelMapper) {
		super();
		this.usersRepo = usersRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		System.out.println("Inside saveUser method");
		User user = convertToUserEntity(userDTO);
		return convertToUserDTO(usersRepo.save(user));
	}

	@Override
	@CachePut(value = "UserDTO", key = "#userDTO.userId")
	public UserDTO updateUser(UserDTO userDTO) {
		System.out.println("Inside updateUser method");
		User user = convertToUserEntity(userDTO);
		return convertToUserDTO(usersRepo.save(user));
	}

	@Override
	@Cacheable(value = "UserDTO")
	public List<UserDTO> getAllUsers() {
		System.out.println("Inside getAllUsers method");
		Iterable<User> listOfAllUsers = usersRepo.findAll();
		System.out.println("getAllUsers");
		List<User> result = StreamSupport.stream(listOfAllUsers.spliterator(), false).collect(Collectors.toList());
		if (result.isEmpty()) {
			return new ArrayList<UserDTO>();
		} else {
			return result.stream().map(this::convertToUserDTO).collect(Collectors.toList());
		}
	}

	@Override
	@Cacheable(value = "UserDTO")
	public List<UserDTO> getAllUsersWithPagination(Integer pageNo, Integer pageSize) {
		System.out.println("Inside getAllUsersWithPagination mehtod");
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> pagedResult = usersRepo.findAll(pageable);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent().stream().map(this::convertToUserDTO).collect(Collectors.toList());
		} else {
			return new ArrayList<User>().stream().map(this::convertToUserDTO).collect(Collectors.toList());
		}
	}

	@Override
	@CacheEvict(value = "UserDTO", key = "#id", allEntries = true)
	public void deleteUserById(int id) {
		System.out.println("Inside Delete Method");
		usersRepo.deleteByUserId(id);

	}

	@Override
	@Cacheable(value = "UserDTO", key = "#id")
	public UserDTO findById(int id) {
		System.out.println("Inside findById Method");
		User user = null;
		Optional<User> userById = usersRepo.findById(id);
		if (userById.isPresent()) {
			user = userById.get();
			return convertToUserDTO(user);
		} else {
			return null;
		}

	}

	@Override
	public UserDTO patchUpdateUser(int id, Map<Object, Object> userObj) {
		System.out.println("Inside patchUpdateUser Method");
		Optional<User> existingUser = usersRepo.findById(id);
		if (existingUser.isPresent()) {
			userObj.forEach((key, value) -> {
				Field field = ReflectionUtils.findRequiredField(User.class, (String) key);
				field.setAccessible(true);
				System.out.println(key + "--->" + field.getName() + "---->" + value);
				ReflectionUtils.setField(field, existingUser.get(), value);
			});
			User updatedUser = usersRepo.save(existingUser.get());
			return convertToUserDTO(updatedUser);
		} else {
			return null;
		}
	}

	private UserDTO convertToUserDTO(User user) {
		UserDTO usersDTO = modelMapper.map(user, UserDTO.class);
		return usersDTO;
	}

	private User convertToUserEntity(UserDTO userDTO) {
		User users = modelMapper.map(userDTO, User.class);
		return users;
	}

}
