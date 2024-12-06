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
		//社員オブジェクトを戻す
		return shain;

	}

	@Override
	public void insertShain(Shain shain) {
		shainRepository.insertShain(shain);

	}

	@Override
	public Shain findByShainId(int shainId) {
		return shainRepository.findByShainId(shainId);
	}

	@Override
	public void updateShain(Shain shain) {
		shainRepository.updateShain(shain);

	}

	@Override
	public void deleteShain(int shainId) {
		shainRepository.deleteShain(shainId);

	}

	@Override
	public void copyShain(Shain shain) {
		shainRepository.copyShain(shain);
	}
}
