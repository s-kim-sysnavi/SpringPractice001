package searchman.service;

import java.util.List;

import org.springframework.stereotype.Service;

import searchman.entity.Shain;

@Service
public interface ShainService {
	//全社員を取得
	List<Shain> findAll();

	//リクエストから社員オブジェクトを作成
	Shain makeShain(Shain request);

	//社員挿入
	void insertShain(Shain shain);

	//社員IDによる社員取得
	Shain findByShainId(Long shainId);

	//社員更新
	void updateShain(Shain shain);

	void updateProfileImage(Long shainId, String profileImage);

	//社員削除
	void deleteShain(Long shainId);
	//
	//	String saveFile(MultipartFile file);

}
