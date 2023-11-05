package com.halim.service;

import com.halim.dao.AddressRepository;
import com.halim.dto.AddressDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.UserAddress;
import com.mongodb.client.result.UpdateResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
    private AddressRepository addressRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public  UserAddress addAddress(AddressDto addressDto) {

		UserAddress maximumIdAddress = addressRepository.findTopByOrderByIdDesc();
		UserAddress map = this.modelMapper.map(addressDto, UserAddress.class);
		if(maximumIdAddress==null){
			map.setId(100);
		}else {
			map.setId(maximumIdAddress.getId()+100);
		}
		UserAddress save = addressRepository.save(map);
		return save;


	}


	public UserAddress updateAddressById(UserAddress address) throws RecordNotFound {
		UserAddress userAdd = addressRepository.findByIsActiveAndId(true, address.getId());
		if(userAdd==null){
			throw new RecordNotFound("Address Not found By Id "+address.getId());
		}
//		UserAddress userAddress = this.modelMapper.map(address, UserAddress.class);
		UserAddress save = addressRepository.save(address);
		 return save;
	}



	public boolean deleteAddress(Integer id) throws RecordNotFound {
		UserAddress byIsActiveAndId = addressRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Address ID Does not exists "+id);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("isActive",false);
		update.set("deleted",true);

		UpdateResult updateResult = mongoTemplate.upsert(query,update,UserAddress.class);
		System.out.println("category item deleted"+id+"\t"+updateResult.getMatchedCount());
		return true;
	}


	public List<UserAddress> findAllAddressByUserId(Integer userid) {
		List<UserAddress> byIsActiveAndUserId = addressRepository.findByIsActiveAndUserId(true, userid);
		return byIsActiveAndUserId;

	}

	public UserAddress findAddressByAddressId(Integer id) throws RecordNotFound {
		UserAddress byIsActiveAndId = addressRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Address Not found By Id "+id);
		}else return byIsActiveAndId;

	}
}
