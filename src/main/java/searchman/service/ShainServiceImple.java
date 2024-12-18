package searchman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import searchman.entity.Shain;
import searchman.repository.ShainRepository;

@Service
public class ShainServiceImple implements ShainService {

	@Autowired
	ShainRepository shainRepository;

	@Override
	public List<Shain> findAll() {
		return shainRepository.findAll();
	}

	@Override
	public Shain makeShain(Shain request) {
		//社員オブジェクトの作成
		Shain shain = new Shain();
		//社員オブジェクトに値を代入
		shain.setId(request.getId());
		shain.setName(request.getName());
		shain.setSei(request.getSei());
		shain.setNen(request.getNen());
		shain.setAddress(request.getAddress());
		shain.setUserId(request.getUserId());
		//社員オブジェクトを戻す
		return shain;

	}

	//	@Override
	//	public Shain makeShain2(Shain request, int userId, String email) {
	//		//社員オブジェクトの作成
	//		Shain shain = new Shain();
	//		//社員オブジェクトに値を代入
	//		shain.setId(request.getId());
	//		shain.setName(request.getName());
	//		shain.setSei(request.getSei());
	//		shain.setNen(request.getNen());
	//		shain.setAddress(request.getAddress());
	//		shain.setEmail(email);
	//		shain.setUserId(userId);
	//
	//		//社員オブジェクトを戻す
	//		return shain;
	//
	//	}

	@Override
	public void insertShain(Shain shain) {
		shainRepository.insertShain(shain);

	}

	@Override
	public Shain findByShainId(Long shainId) {
		return shainRepository.findByShainId(shainId);
	}

	@Override
	public void updateShain(Shain shain) {
		shainRepository.updateShain(shain);

	}

	@Override
	public void deleteShain(Long shainId) {
		shainRepository.deleteShain(shainId);

	}

	//	@Override
	//	public void copyShain(Shain shain) {
	//		shainRepository.copyShain(shain);
	//	}
}
